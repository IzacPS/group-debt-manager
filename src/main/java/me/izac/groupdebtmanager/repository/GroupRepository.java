package me.izac.groupdebtmanager.repository;

import me.izac.groupdebtmanager.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    @Query("select g from Group g join g.users u where u.id = ?1")
    List<Group> findAllByUserId(Long userId);

    @Query("select g from Group g join g.users u where u.email = ?1")
    List<Group> findAllByUserEmail(String email);
}
