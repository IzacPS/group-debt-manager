package me.izac.groupdebtmanager.dto;

import lombok.*;
import me.izac.groupdebtmanager.model.Debt;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DebtCompleteDTO {
    private Long id;
    private double amount;
    private Long creditor;
    private Debt.Status status;
    private List<Long> debtors;
    private Long group;
}
