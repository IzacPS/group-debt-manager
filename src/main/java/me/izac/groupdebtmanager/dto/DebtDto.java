package me.izac.groupdebtmanager.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@Builder
public class DebtDto {
    private double amount;
    private double amountPaid;
    private String status;
    private String description;
    private Date debtDate;
    private MemberDto debtor;

    private MemberDto creditor;
    private GroupDto group;
}
