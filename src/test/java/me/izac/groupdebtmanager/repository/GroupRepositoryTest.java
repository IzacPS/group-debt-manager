package me.izac.groupdebtmanager.repository;

import me.izac.groupdebtmanager.model.Group;
import me.izac.groupdebtmanager.model.User;
import org.junit.jupiter.api.BeforeAll;
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
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DataJpaTest
@ActiveProfiles("test")
public class GroupRepositoryTest {

    @Autowired
    GroupRepository groupRepository;
    Group group;

    @BeforeAll
    void newGroup(){
        group = Group.builder()
                .id(1L)
                .name("Group First")
                .description("The First Group")
                .users(Set.of())
                .debts(Set.of())
                .build();
    }

    @Test
    @DisplayName("Save new Group to database")
    void saveGroup() {
        Group retGroup = groupRepository.save(group);

        assertEquals(retGroup, group);
    }

    @Test
    @DisplayName("Update existing group")
    void updateGroup() {
        Group updatedGroup = group.toBuilder().name("New Group Name").build();
        Group retGroup = groupRepository.save(updatedGroup);

        assertEquals(retGroup, updatedGroup);
        assertNotEquals(retGroup, group);
    }
    @Test
    @Sql("/scripts/new_group.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("Find Group by id")
    void findGroupById() {
        Optional<Group> ret = groupRepository.findById(1L);
        assertTrue(ret.isPresent());
        assertEquals(ret.get(), group);

        ret = groupRepository.findById(10L);
        assertFalse(ret.isPresent());
    }

    @Test
    @Sql("/scripts/list_of_groups.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("Find list of existing Groups")
    void listAllGroups() {
        List<Group> groupList = groupRepository.findAll();
        assertFalse(groupList.isEmpty());

        assertEquals(groupList.size(), 5);
    }
}
