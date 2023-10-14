package me.izac.groupdebtmanager.repository;

import me.izac.groupdebtmanager.model.DebtDebtor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DebtDebtorRepository extends JpaRepository<DebtDebtor, Long> {
    @Query("select dd from DebtDebtor dd where dd.debt.id = ?1")
    List<DebtDebtor> findAllByDebtId(Long debtId);

    @Query("select dd from DebtDebtor dd where dd.debtor.id = ?1")
    List<DebtDebtor> findAllByDebtorId(Long debtorId);
}
