package me.izac.groupdebtmanager.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import me.izac.groupdebtmanager.dto.DebtDto;
import me.izac.groupdebtmanager.dto.MemberDto;

import java.util.Date;

@Setter
@Getter
@Builder
@Entity
@Table(name = "tb_debt")
public class Debt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double amount;
    private double amountPaid;

    @Enumerated(EnumType.ORDINAL)
    private Status status;
    private String description;
    private Date debtDate;

    @ManyToOne
    @JoinColumn(name = "debtor_id")
    private Member debtor;

    @ManyToOne
    @JoinColumn(name = "creditor_id")
    private Member creditor;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;


    public enum Status{
        PENDING,
        PAID
    }

    public DebtDto getDebtDto(){
        return DebtDto.builder()
                .amount(this.amount)
                .amountPaid(this.amountPaid)
                .status(this.status.toString())
                .description(this.description)
                .debtDate(this.debtDate)
                .debtor(this.debtor.toMemberDto())
                .creditor(this.creditor.toMemberDto())
                .group(this.group.getGroupDto()).build();
    }

}
