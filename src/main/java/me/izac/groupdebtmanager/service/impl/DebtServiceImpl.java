package me.izac.groupdebtmanager.service.impl;

import lombok.RequiredArgsConstructor;
import me.izac.groupdebtmanager.dto.*;
import me.izac.groupdebtmanager.model.Debt;
import me.izac.groupdebtmanager.model.DebtDebtor;
import me.izac.groupdebtmanager.model.Group;
import me.izac.groupdebtmanager.model.User;
import me.izac.groupdebtmanager.repository.DebtDebtorRepository;
import me.izac.groupdebtmanager.repository.DebtRepository;
import me.izac.groupdebtmanager.repository.GroupRepository;
import me.izac.groupdebtmanager.repository.UserRepository;
import me.izac.groupdebtmanager.service.IDebtService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DebtServiceImpl implements IDebtService {
    private final DebtRepository debtRepository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final DebtDebtorRepository debtDebtorRepository;

    @Override
    public DebtDTO createDebt(CreateDebtDTO debtDTO) {
        User user = userRepository.findById(debtDTO.getCreditor()).orElseThrow();
        Group group = groupRepository.findById(debtDTO.getGroupId()).orElseThrow();
        Debt debt = debtDTO.toDebt();
        debt.setCreditor(user);
        debt.setGroup(group);
        return debtRepository.save(debt).toDebtDTO();
    }

    @Override
    public DebtCompleteDTO getDebtById(Long debtId) {
        //List<Long> debts = debtDebtorRepository.findAllByDebtId(debtId).stream().map(d -> d.getDebtor().getId()).toList();
        return debtRepository.findById(debtId).orElseThrow().toDebtCompleteDTO();
    }

    @Override
    public List<DebtCompleteDTO> listAllDebtsFromDebtorById(Long userId) {
        //            List<Long> debts = debtDebtorRepository.findAllByDebtId(debt.getId()).stream().map(d -> d.getDebtor().getId()).toList();
        return debtRepository.findAllByDebtorsId(userId).stream().map(Debt::toDebtCompleteDTO).toList();
    }

    @Override
    public List<DebtCompleteDTO> listAllDebtsFromDebtorByEmail(String email) {
        //            List<Long> debts = debtDebtorRepository.findAllByDebtId(debt.getId()).stream().map(d -> d.getDebtor().getId()).toList();
        return debtRepository.findAllByDebtorsEmail(email).stream().map(Debt::toDebtCompleteDTO).toList();
    }

    @Override
    public List<DebtCompleteDTO> listAllDebtsFromCreditorById(Long userId) {
        //            List<Long> debts = debtDebtorRepository.findAllByDebtId(debt.getId()).stream().map(d -> d.getDebtor().getId()).toList();
        return debtRepository.findAllByCreditorId(userId).stream().map(Debt::toDebtCompleteDTO).toList();
    }

    @Override
    public List<DebtCompleteDTO> listAllDebtsFromCreditorByEmail(String email) {
        //            List<Long> debts = debtDebtorRepository.findAllByDebtId(debt.getId()).stream().map(d -> d.getDebtor().getId()).toList();
        return debtRepository.findAllByCreditorEmail(email).stream().map(Debt::toDebtCompleteDTO).toList();
    }

    @Override
    public List<DebtCompleteDTO> listAllDebtsFromGroup(Long groupId) {
        //            List<Long> debts = debtDebtorRepository.findAllByDebtId(debt.getId()).stream().map(d -> d.getDebtor().getId()).toList();
        return debtRepository.findAllByGroupId(groupId).stream().map(Debt::toDebtCompleteDTO).toList();
    }

    @Override
    public DebtCompleteDTO updateDebtStatus(Long debtId, Debt.Status status) {
        Debt debt = debtRepository.findById(debtId).orElseThrow();
//        List<Long> debts = debtDebtorRepository.findAllByDebtId(debtId).stream().map(d -> d.getDebtor().getId()).toList();
        return debtRepository.save(debt).toDebtCompleteDTO();
    }

    @Override
    public DebtCompleteDTO updateDebt(CreateDebtDTO debtDTO, Long debtId) {
        Debt debt = debtRepository.findById(debtId).orElseThrow();
        Debt newDebt = debtDTO.toDebt();
        newDebt.setCreditor(debt.getCreditor());
        newDebt.setGroup(debt.getGroup());
        newDebt.setId(debt.getId());
        debt = debtRepository.save(newDebt);
//        List<Long> debts = debtDebtorRepository.findAllByDebtId(debtId).stream().map(d -> d.getDebtor().getId()).toList();
        return debt.toDebtCompleteDTO();
    }

    @Override
    public List<DebtDebtorDTO> payDebt(ListOfIdsDTO debts, Long userId) {
        List<DebtDebtorDTO> retDTO = new ArrayList<>();
        for (Long i : debts.getIds()){
            Debt debt = debtRepository.findById(i).orElseThrow();
            DebtDebtor debtDebtor = debt.getDebtors().stream()
                    .filter(dd -> dd.getDebt().getId() == i && dd.getDebtor().getId() == userId)
                    .findFirst().orElse(null);

            if(debtDebtor != null) {
                debtDebtor.setStatus(Debt.Status.PAID);
                retDTO.add(debt.toDebtDebtorDTO(Debt.Status.PAID));
            }

            boolean isAllPaid = debt.getDebtors().
                    stream().filter(dd -> dd.getDebt().getId() == i)
                    .noneMatch(dd -> dd.getStatus() == Debt.Status.PENDING);

            if(isAllPaid){
                debt.setStatus(Debt.Status.PAID);
            }
            debtRepository.save(debt);
        }

        return retDTO;
    }

    @Override
    public DebtCompleteDTO divideDebt(ListOfIdsDTO ids, Long debtId) {
        Debt debt = debtRepository.findById(debtId).orElseThrow();

        debt.setAmountPerUser(debt.getAmountPerUser() / (double) ids.getIds().size());

        for(Long i : ids.getIds()){
            User u = userRepository.findById(i).orElseThrow();

            DebtDebtor debtDebtor = DebtDebtor.builder()
                    .debtor(u)
                    .status(Debt.Status.PENDING)
                    .debt(debt)
                    .build();

            u.getDebtsAsDebtor().add(debtDebtor);

            debtDebtor = debtDebtorRepository.save(debtDebtor);

            debt.getDebtors().add(debtDebtor);
        }
        return debtRepository.save(debt).toDebtCompleteDTO();
    }
}
