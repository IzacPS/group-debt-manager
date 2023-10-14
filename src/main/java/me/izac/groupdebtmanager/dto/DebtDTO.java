package me.izac.groupdebtmanager.dto;

import lombok.*;
import me.izac.groupdebtmanager.model.Debt;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DebtDTO {
    private Long id;
    private double amount;
    private Long creditor;
    private Long group;
    private Debt.Status status;
}
