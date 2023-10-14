package me.izac.groupdebtmanager.service;

import me.izac.groupdebtmanager.dto.*;
import me.izac.groupdebtmanager.model.Debt;

import java.util.List;

public interface IDebtService {
    DebtDTO createDebt(CreateDebtDTO debtDTO);
    DebtCompleteDTO getDebtById(Long debtId);
    List<DebtCompleteDTO> listAllDebtsFromDebtorById(Long userId);
    List<DebtCompleteDTO> listAllDebtsFromDebtorByEmail(String email);
    List<DebtCompleteDTO> listAllDebtsFromCreditorById(Long userId);
    List<DebtCompleteDTO> listAllDebtsFromCreditorByEmail(String email);

    List<DebtCompleteDTO> listAllDebtsFromGroup(Long groupId);
    DebtCompleteDTO updateDebtStatus(Long debtId, Debt.Status status);
    DebtCompleteDTO updateDebt(CreateDebtDTO debtDTO, Long debtId);

    List<DebtDebtorDTO> payDebt(ListOfIdsDTO debts, Long userId);

    DebtCompleteDTO divideDebt(ListOfIdsDTO ids, Long debtId);
}