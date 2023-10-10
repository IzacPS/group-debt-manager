package me.izac.groupdebtmanager.service.impl;

import lombok.RequiredArgsConstructor;
import me.izac.groupdebtmanager.model.Debt;
import me.izac.groupdebtmanager.model.Group;
import me.izac.groupdebtmanager.model.Member;
import me.izac.groupdebtmanager.repository.DebtRepository;
import me.izac.groupdebtmanager.service.IDebtService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DebtServiceImpl implements IDebtService {
    private final DebtRepository debtRepository;
    @Override
    public Debt createDebt(Member debtor, Member creditor, Group group, double amount) {
        Debt debt = Debt.builder()
                .debtor(debtor)
                .creditor(creditor)
                .amount(amount)
                .status(Debt.Status.PENDING).build();

        return debtRepository.save(debt);
    }

    @Override
    public List<Debt> findDebtsForMemberInGroup(Member member, Group group) {
        return debtRepository.findDebtsForMemberInGroup(member.getId(), group.getId());
    }

    @Override
    public List<Debt> findCreditsForMemberInGroup(Member member, Group group) {
        return debtRepository.findCreditsForMemberInGroup(member.getId(), group.getId());
    }

    @Override
    public List<Debt> findAllDebtsOfAMember(Member member) {
        return debtRepository.findByDebtorId(member.getId());
    }

    @Override
    public List<Debt> findAllCreditsOfAMember(Member member) {
        return debtRepository.findByCreditorId(member.getId());
    }

    @Override
    public Debt updateDebtStatusToPaid(Debt debt) {
        debt.setStatus(Debt.Status.PAID);
        return debtRepository.save(debt);
    }

    @Override
    public Debt updateDebtPaidAmount(Debt debt, double amount) {
        double e = 0.000001;
        double paidSoFar = debt.getAmountPaid() + amount;
        debt.setAmountPaid(paidSoFar);
        if(debt.getAmount() - paidSoFar <= e){
            debt.setStatus(Debt.Status.PAID);
        }
        return debtRepository.save(debt);
    }
}
