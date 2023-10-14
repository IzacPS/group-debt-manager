package me.izac.groupdebtmanager.repository;

import me.izac.groupdebtmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);

    @Query("select u from User u join u.groups g where g.id = ?1")
    List<User> findAllByGroupId(Long groupId);
}
