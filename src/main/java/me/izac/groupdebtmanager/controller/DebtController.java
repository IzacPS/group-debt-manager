package me.izac.groupdebtmanager.controller;

import lombok.RequiredArgsConstructor;
import me.izac.groupdebtmanager.dto.CreateDebtDTO;
import me.izac.groupdebtmanager.dto.DebtDTO;
import me.izac.groupdebtmanager.service.IDebtService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/debts")
public class DebtController {
    private final IDebtService debtService;

    @PostMapping
    DebtDTO createDebt(@RequestBody CreateDebtDTO debtDTO){
        return debtService.createDebt(debtDTO);
    }

    @PutMapping("/{debtId}")
    DebtDTO updateDebt(@RequestBody CreateDebtDTO debtDTO, @PathVariable Long debtId){
        return debtService.updateDebt(debtDTO, debtId);
    }

    @GetMapping("/{debtId}")
    DebtDTO getDebt(@PathVariable Long debtId){
        return debtService.getDebtById(debtId);
    }
}
