package me.izac.groupdebtmanager.repository;

import me.izac.groupdebtmanager.model.Group;
import me.izac.groupdebtmanager.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}
