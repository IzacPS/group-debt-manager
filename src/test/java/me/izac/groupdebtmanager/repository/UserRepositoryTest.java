package me.izac.groupdebtmanager.repository;

import me.izac.groupdebtmanager.model.Group;
import me.izac.groupdebtmanager.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {
    @Autowired UserRepository userRepository;

    @Test
    @Sql("/scripts/usergroup.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("Find user by email")
    void findUserByEmail() {
        User user = userRepository.findUserByEmail("carlos@example.com").orElse(null);
        assertNotNull(user);
        assertEquals(user.getId(), 3L);
    }

    @Test
    @Sql("/scripts/usergroup.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("Find all users by group id")
    void findAllByGroupId() {
        List<User> groups = userRepository.findAllByGroupId(1L);
        assertFalse(groups.isEmpty());
        assertEquals(groups.size(), 4);
    }
}