package me.izac.groupdebtmanager.repository;

import me.izac.groupdebtmanager.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findMemberByEmail(String email);
    void deleteMemberByEmail(String email);
}
