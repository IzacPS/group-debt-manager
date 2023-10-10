package me.izac.groupdebtmanager.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import me.izac.groupdebtmanager.model.Debt;

@Getter
@Setter
@Builder
public class DebtDTO {
    private Long id;
    private double amount;
    private double amountPerUser;
    private UserDTO debtor;
    private GroupDTO group;
    private Debt.Status status;
}
