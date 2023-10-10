package me.izac.groupdebtmanager.service;

import me.izac.groupdebtmanager.dto.CreateDebtDTO;
import me.izac.groupdebtmanager.dto.DebtDTO;
import me.izac.groupdebtmanager.model.Debt;

import java.util.List;

public interface IDebtService {
    DebtDTO createDebt(CreateDebtDTO debtDTO);
    DebtDTO getDebtById(Long debtId);
    List<DebtDTO> listAllDebtsFromUser(Long userId);

    List<DebtDTO> listAllDebtsFromGroup(Long groupId);
    DebtDTO updateDebtStatus(Long debtId, Debt.Status status);
    DebtDTO updateDebt(CreateDebtDTO debtDTO, Long debtId);
}
