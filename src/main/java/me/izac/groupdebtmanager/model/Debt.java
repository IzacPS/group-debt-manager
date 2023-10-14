package me.izac.groupdebtmanager.model;

import jakarta.persistence.*;
import lombok.*;
import me.izac.groupdebtmanager.dto.DebtCompleteDTO;
import me.izac.groupdebtmanager.dto.DebtDTO;
import me.izac.groupdebtmanager.dto.DebtDebtorDTO;

import java.util.Date;
import java.util.Set;

@Setter
@Getter
@Builder
@Entity
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
    @JoinColumn(name = "creditor_id")
    private User creditor;

    @OneToMany(mappedBy = "debt", fetch = FetchType.EAGER)
    private Set<DebtDebtor> debtors;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    public DebtDTO toDebtDTO(){
        return DebtDTO.builder()
                .id(this.id)
                .amount(this.amount)
                .creditor(this.creditor.getId())
                .group(this.group.getId())
                .status(this.status)
                .build();

    }

    public DebtCompleteDTO toDebtCompleteDTO(){
        return DebtCompleteDTO.builder()
                .id(this.id)
                .amount(this.amount)
                .debtors(this.debtors.stream().map(d -> d.getDebtor().getId()).toList())
                .creditor(this.creditor.getId())
                .status(this.status)
                .group(this.group.getId())
                .build();

    }
     public DebtDebtorDTO toDebtDebtorDTO(Debt.Status status){
        return DebtDebtorDTO.builder()
                .id(this.id)
                .amount(this.amountPerUser)
                .creditor(this.creditor.getId())
                .status(status)
                .group(this.group.getId())
                .build();
     }

    public enum Status{
        PENDING,
        PAID
    }
}
