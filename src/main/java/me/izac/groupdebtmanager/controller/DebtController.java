package me.izac.groupdebtmanager.controller;

import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary= "Criar um novo débito")
    @PostMapping
    DebtDTO createDebt(@RequestBody CreateDebtDTO debtDTO){
        return debtService.createDebt(debtDTO);
    }

    @Operation(summary= "Atualizar um débito existente")
    @PutMapping("/{debtId}")
    DebtDTO updateDebt(@RequestBody CreateDebtDTO debtDTO, @PathVariable Long debtId){
        return debtService.updateDebt(debtDTO, debtId);
    }

    @Operation(summary= "Buscar débito")
    @GetMapping("/{debtId}")
    DebtDTO getDebt(@PathVariable Long debtId){
        return debtService.getDebtById(debtId);
    }
}
