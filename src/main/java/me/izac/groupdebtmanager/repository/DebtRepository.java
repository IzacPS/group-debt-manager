package me.izac.groupdebtmanager.repository;

import me.izac.groupdebtmanager.model.Debt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DebtRepository extends JpaRepository<Debt, Long> {
    @Query("select d from Debt d where d.debtor.id = ?1")
    List<Debt> findAllDebtsFromUser(Long userId);

    @Query("select d from Debt d where d.group.id = ?1")
    List<Debt> findAllDebtsFromGroup(Long groupId);
}
