package me.izac.groupdebtmanager.dto;

import lombok.*;
import me.izac.groupdebtmanager.model.Debt;
import me.izac.groupdebtmanager.model.Group;
import me.izac.groupdebtmanager.model.User;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateDebtDTO {
    private String description;
    private double amount;
    private Long creditor;
    private Long groupId;
    private Date date;

    public Debt toDebt(){
        return Debt.builder()
                .amount(this.amount)
                .debtors(Set.of())
                .status(Debt.Status.PENDING)
                .description(this.description)
                .date(this.date)
                .build();
    }
}
