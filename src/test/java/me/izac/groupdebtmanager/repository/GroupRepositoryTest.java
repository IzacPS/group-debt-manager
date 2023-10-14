package me.izac.groupdebtmanager.repository;

import me.izac.groupdebtmanager.model.Group;
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
class GroupRepositoryTest {

    @Autowired GroupRepository groupRepository;
    @Test
    @Sql("/scripts/usergroup.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("Find all groups by user id")
    void findAllByUserId() {
        List<Group> groups = groupRepository.findAllByUserId(3L);
        assertFalse(groups.isEmpty());
        assertEquals(groups.size(), 2);
    }

    @Test
    @Sql("/scripts/usergroup.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("Find all groups by user email")
    void findAllByUserEmail() {
        List<Group> groups = groupRepository.findAllByUserEmail("carlos@example.com");
        assertFalse(groups.isEmpty());
        assertEquals(groups.size(), 2);
    }
}