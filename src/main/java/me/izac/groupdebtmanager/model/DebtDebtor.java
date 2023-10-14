package me.izac.groupdebtmanager.model;


import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class DebtDebtor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "debt_id")
    private Debt debt;

    @ManyToOne
    @JoinColumn(name = "debtor_id")
    private User debtor;

    @Enumerated(EnumType.STRING)
    private Debt.Status status;
}
