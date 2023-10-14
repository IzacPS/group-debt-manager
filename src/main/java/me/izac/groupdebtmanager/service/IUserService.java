package me.izac.groupdebtmanager.service;

import me.izac.groupdebtmanager.dto.CreateUserDTO;
import me.izac.groupdebtmanager.dto.UserCompleteDTO;
import me.izac.groupdebtmanager.dto.UserDTO;

import java.util.List;

public interface IUserService {
    UserDTO createUser(CreateUserDTO user);
    UserCompleteDTO updateUser(CreateUserDTO userDTO, Long userId);
    UserCompleteDTO findUserById(Long id);
    UserCompleteDTO findUserByEmail(String email);
}
