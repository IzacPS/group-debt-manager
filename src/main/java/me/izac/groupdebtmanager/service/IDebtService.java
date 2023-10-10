package me.izac.groupdebtmanager.service;

import me.izac.groupdebtmanager.model.Debt;
import me.izac.groupdebtmanager.model.Group;
import me.izac.groupdebtmanager.model.Member;

import java.util.List;

public interface IDebtService {
    Debt createDebt(Member member, Member creditor, Group group, double amount);
    List<Debt> findDebtsForMemberInGroup(Member member, Group group);
    List<Debt> findCreditsForMemberInGroup(Member member, Group group);
    List<Debt> findAllDebtsOfAMember(Member member);
    List<Debt> findAllCreditsOfAMember(Member member);

    Debt updateDebtStatusToPaid(Debt debt);
    Debt updateDebtPaidAmount(Debt debt, double amount);
}
