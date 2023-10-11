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
class UserGroupRepositoryTest {
    @Autowired UserGroupRepository userGroupRepository;

    @Test
    @Sql("/scripts/usergroup.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("Find all users of a group")
    void findAllUsersInGroup() {
        List<User> userList = userGroupRepository.findAllUsersInGroup(2L);
        assertFalse(userList.isEmpty());
        assertEquals(userList.size(), 4);
    }

    @Test
    @Sql("/scripts/usergroup.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("Find all groups of an user")
    void findAllGroupsOfUser() {
        List<Group> groupList = userGroupRepository.findAllGroupsOfUser(3L);
        assertFalse(groupList.isEmpty());
        assertEquals(groupList.size(), 2);
    }
}