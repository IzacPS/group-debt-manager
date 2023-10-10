package me.izac.groupdebtmanager.service;

import me.izac.groupdebtmanager.dto.CreateUserDTO;
import me.izac.groupdebtmanager.dto.UserDTO;

import java.util.List;

public interface IUserService {
    UserDTO createUser(CreateUserDTO user);
    UserDTO updateUser(CreateUserDTO userDTO, Long userId);
    UserDTO findUserById(Long id);
    UserDTO findUserByEmail(String email);
}
