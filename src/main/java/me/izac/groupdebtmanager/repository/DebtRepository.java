package me.izac.groupdebtmanager.repository;

import me.izac.groupdebtmanager.model.Debt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DebtRepository extends JpaRepository<Debt, Long> {
    @Query("select d from Debt d join d.debtors ds where ds.debtor.id = ?1")
    List<Debt> findAllByDebtorsId(Long userId);

    @Query("select d from Debt d join d.debtors ds where ds.debtor.email = ?1")
    List<Debt> findAllByDebtorsEmail(String email);


    @Query("select d from Debt d where d.creditor.id = ?1")
    List<Debt> findAllByCreditorId(Long userId);

    @Query("select d from Debt d join d.creditor ds where ds.email = ?1")
    List<Debt> findAllByCreditorEmail(String email);

    @Query("select d from Debt d where d.group.id = ?1")
    List<Debt> findAllByGroupId(Long groupId);
}
