package me.izac.groupdebtmanager.dto;

import lombok.*;
import me.izac.groupdebtmanager.model.Debt;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DebtDebtorDTO {
    private Long id;
    private double amount;
    private Long creditor;
    private Debt.Status status;
    private Long group;
}
