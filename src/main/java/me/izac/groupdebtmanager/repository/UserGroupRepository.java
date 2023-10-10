package me.izac.groupdebtmanager.repository;

import me.izac.groupdebtmanager.dto.UserDTO;
import me.izac.groupdebtmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserGroupRepository extends JpaRepository<User, Long> {

    @Query("select u from User u join u.groups g where g.id = ?1")
    List<User> findAllUsersInGroup(Long groupId);
}
