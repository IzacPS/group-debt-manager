package me.izac.groupdebtmanager.model;

import jakarta.persistence.*;
import lombok.*;
import me.izac.groupdebtmanager.dto.DebtDTO;
import org.apache.commons.lang3.builder.EqualsExclude;

import java.util.Date;

@Setter
@Getter
@Builder
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_debt")
public class Debt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double amount;
    private double amountPerUser;

    @Enumerated(EnumType.STRING)
    private Status status;
    private String description;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "debtor_id")
    private User debtor;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    public DebtDTO toDebtDTO(){
        return DebtDTO.builder()
                .id(this.id)
                .amount(this.amount)
                .amountPerUser(this.amountPerUser)
                .debtor(this.debtor.toUserDTO())
                .group(this.group.toGroupDTO())
                .status(this.status)
                .build();

    }

    public enum Status{
        PENDING,
        PAID
    }
}
