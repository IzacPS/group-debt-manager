package me.izac.groupdebtmanager.service;

import me.izac.groupdebtmanager.dto.CreateUserDTO;
import me.izac.groupdebtmanager.dto.UserDTO;
import me.izac.groupdebtmanager.model.User;
import me.izac.groupdebtmanager.repository.UserRepository;
import me.izac.groupdebtmanager.service.impl.UserServiceImpl;
import org.h2.command.ddl.CreateUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class IUserServiceTest {
    @Mock
    private UserRepository userRepository;// = Mockito.mock(UserRepository.class);
    @Test
    @DisplayName("Cria um e retorna um usuário")
    void createUser() {
        IUserService userService = new UserServiceImpl(userRepository);

        CreateUserDTO userDTOCreate = CreateUserDTO.builder()
                .firstName("Izac")
                .lastName("Santos")
                .email("izacmail@gmail.com")
                .password("Password")
                .build();

        UserDTO userDTO = UserDTO.builder()
                .id(1L)
                .firstName("Izac")
                .lastName("Santos")
                .email("izacmail@gmail.com")
                .build();

        User user = userDTOCreate.toUser();
        User userRes = userDTOCreate.toUser();
        userRes.setId(1L);

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenAnswer(i -> {
            User u = (User)i.getArguments()[0];
            u.setId(1L);
            return u;
        });

        UserDTO userDTOResponse = userService.createUser(userDTOCreate);

        Assertions.assertEquals(userDTOResponse.getId(), userDTO.getId());
        Assertions.assertEquals(userDTOResponse.getEmail(), userDTO.getEmail());
        Assertions.assertEquals(userDTOResponse.getFirstName(), userDTO.getFirstName());
        Assertions.assertEquals(userDTOResponse.getLastName(), userDTO.getLastName());
    }

    @Test
    void updateUser() {
    }

    @Test
    @DisplayName("Busca usuário pelo id")
    void findUserById() {
        IUserService userService = new UserServiceImpl(userRepository);
        User user = User.builder().id(100L)
                .email("izacmail@gmail.com")
                .firstName("Izac")
                .lastName("Santos")
                .password("Password").build();

        UserDTO userDTO = UserDTO.builder()
                .id(100L)
                .firstName("Izac")
                .lastName("Santos")
                .email("izacmail@gmail.com")
                .build();

        Mockito.when(userRepository.findById(100L)).thenReturn(Optional.of(user));

        UserDTO userDTOResponse = userService.findUserById(100L);

        Assertions.assertEquals(userDTOResponse.getId(), userDTO.getId());
        Assertions.assertEquals(userDTOResponse.getEmail(), userDTO.getEmail());
        Assertions.assertEquals(userDTOResponse.getFirstName(), userDTO.getFirstName());
        Assertions.assertEquals(userDTOResponse.getLastName(), userDTO.getLastName());
    }

    @Test

    void findUserByEmail() {
        IUserService userService = new UserServiceImpl(userRepository);
        User user = User.builder().id(1L)
                .email("myemail@gmail.com")
                .firstName("Izac")
                .lastName("Santos")
                .password("Password").build();

        UserDTO userDTO = UserDTO.builder()
                .id(1L)
                .firstName("Izac")
                .lastName("Santos")
                .email("myemail@gmail.com")
                .build();

        Mockito.when(userRepository.findUserByEmail("myemail@gmail.com")).thenReturn(Optional.of(user));

        UserDTO userDTResponse = userService.findUserByEmail("myemail@gmail.com");

        Assertions.assertEquals(userDTResponse.getId(), userDTO.getId());
        Assertions.assertEquals(userDTResponse.getEmail(), userDTO.getEmail());
        Assertions.assertEquals(userDTResponse.getFirstName(), userDTO.getFirstName());
        Assertions.assertEquals(userDTResponse.getLastName(), userDTO.getLastName());
    }
}