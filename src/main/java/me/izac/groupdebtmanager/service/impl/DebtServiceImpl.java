package me.izac.groupdebtmanager.service.impl;

import lombok.RequiredArgsConstructor;
import me.izac.groupdebtmanager.dto.CreateDebtDTO;
import me.izac.groupdebtmanager.dto.DebtDTO;
import me.izac.groupdebtmanager.model.Debt;
import me.izac.groupdebtmanager.model.Group;
import me.izac.groupdebtmanager.model.User;
import me.izac.groupdebtmanager.repository.DebtRepository;
import me.izac.groupdebtmanager.repository.GroupRepository;
import me.izac.groupdebtmanager.repository.UserRepository;
import me.izac.groupdebtmanager.service.IDebtService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DebtServiceImpl implements IDebtService {
    private final DebtRepository debtRepository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    @Override
    public DebtDTO createDebt(CreateDebtDTO debtDTO) {
        User user = userRepository.findById(debtDTO.getDebtorId()).orElseThrow();
        Group group = groupRepository.findById(debtDTO.getGroupId()).orElseThrow();
        Debt debt = debtDTO.toDebt(user, group);
        return debtRepository.save(debt).toDebtDTO();
    }

    @Override
    public DebtDTO getDebtById(Long debtId) {
        return debtRepository.findById(debtId).orElseThrow().toDebtDTO();
    }

    @Override
    public List<DebtDTO> listAllDebtsFromUser(Long userId) {
        return debtRepository.findAllDebtsFromUser(userId).stream().map(Debt::toDebtDTO).toList();
    }

    @Override
    public List<DebtDTO> listAllDebtsFromGroup(Long groupId) {
        return debtRepository.findAllDebtsFromGroup(groupId).stream().map(Debt::toDebtDTO).toList();
    }

    @Override
    public DebtDTO updateDebtStatus(Long debtId, Debt.Status status) {
        Debt debt = debtRepository.findById(debtId).orElseThrow();
        debt.setStatus(status);
        return debtRepository.save(debt).toDebtDTO();
    }

    @Override
    public DebtDTO updateDebt(CreateDebtDTO debtDTO, Long debtId) {
        Debt debt = debtRepository.findById(debtId).orElseThrow();
        debt.setId(debtId);
        return debtRepository.save(debt).toDebtDTO();
    }
}
