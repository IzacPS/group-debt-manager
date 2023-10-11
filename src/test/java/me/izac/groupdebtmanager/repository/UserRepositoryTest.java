package me.izac.groupdebtmanager.repository;

import me.izac.groupdebtmanager.model.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DataJpaTest
//@TestPropertySource(properties = {
//        "spring.jpa.hibernate.ddl-auto=validate"
//})
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired UserRepository userRepository;
    User user;

    @BeforeAll
    void newUser(){
        user = User.builder()
                .id(1L)
                .firstName("Izac")
                .lastName("Santos")
                .debts(Set.of())
                .groups(Set.of())
                .password("secret")
                .email("mymail@mail.com")
                .build();
    }

    @Test
    @DisplayName("Save User to database")
    void saveUser() {
        User retUser = userRepository.save(user);

        assertEquals(retUser, user);
    }

    @Test
    @DisplayName("Update existing user")
    void updateUser() {
        User updatedUser = user.toBuilder().lastName("Pereira").build();
        User retUser = userRepository.save(updatedUser);

        assertEquals(retUser, updatedUser);
        assertNotEquals(retUser, user);
    }

    @Test
    @Sql("/scripts/new_user.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("Find User By email")
    void findUserByEmail() {
        Optional<User> ret = userRepository.findUserByEmail("mymail@mail.com");
        assertTrue(ret.isPresent());
        assertEquals(ret.get(), user);

        ret = userRepository.findUserByEmail("anothermail@mail.com");
        assertFalse(ret.isPresent());
    }

    @Test
    @Sql("/scripts/new_user.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("Find User by id")
    void findUserById() {
        Optional<User> ret = userRepository.findById(1L);
        assertTrue(ret.isPresent());
        assertEquals(ret.get(), user);

        ret = userRepository.findById(10L);
        assertFalse(ret.isPresent());
    }

}