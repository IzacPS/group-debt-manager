package me.izac.groupdebtmanager.repository;

import me.izac.groupdebtmanager.model.Debt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DebtRepository extends JpaRepository<Debt, Long> {
    @Query("select u.debts from User u where u.id = ?1")
    List<Debt> findAllDebtsFromUser(Long userId);

    @Query("select g.debts from Group g where g.id = ?1")
    List<Debt> findAllDebtsFromGroup(Long groupId);
}
