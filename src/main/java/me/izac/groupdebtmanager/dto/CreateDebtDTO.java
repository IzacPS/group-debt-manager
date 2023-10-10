package me.izac.groupdebtmanager.dto;

import lombok.Getter;
import lombok.Setter;
import me.izac.groupdebtmanager.model.Debt;
import me.izac.groupdebtmanager.model.Group;
import me.izac.groupdebtmanager.model.User;

import java.util.Date;

@Getter
@Setter
public class CreateDebtDTO {
    private String description;
    private double amount;
    private double amountPerUser;
    private Long debtorId;
    private Long groupId;
    private Date date;

    public Debt toDebt(User debtor, Group group){
        return Debt.builder()
                .amount(this.amount)
                .amountPerUser(this.amountPerUser)
                .debtor(debtor)
                .group(group)
                .description(this.description)
                .status(Debt.Status.PENDING)
                .date(this.date)
                .build();
    }
}
