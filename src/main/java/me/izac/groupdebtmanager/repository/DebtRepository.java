package me.izac.groupdebtmanager.repository;

import me.izac.groupdebtmanager.model.Debt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DebtRepository extends JpaRepository<Debt, Long> {
    @Query("select d from Debt d where d.debtor.id = ?1 and d.group.id = ?2")
    List<Debt> findDebtsForMemberInGroup(Long memberId, Long groupId);

    @Query("select d from Debt d where d.creditor.id = ?1 and d.group.id = ?2")
    List<Debt> findCreditsForMemberInGroup(Long memberId, Long groupId);

    List<Debt> findByDebtorId(Long debtor_id);
    List<Debt> findByCreditorId(Long creditor_id);
}
