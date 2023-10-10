package me.izac.groupdebtmanager.service.impl;

import lombok.RequiredArgsConstructor;
import me.izac.groupdebtmanager.dto.CreateUserDTO;
import me.izac.groupdebtmanager.dto.UserDTO;
import me.izac.groupdebtmanager.model.User;
import me.izac.groupdebtmanager.repository.UserRepository;
import me.izac.groupdebtmanager.service.IUserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;

    @Override
    public UserDTO createUser(CreateUserDTO userDTO) {
        User user = userDTO.toUser();
        return userRepository.save(user).toUserDTO();
    }

    @Override
    public UserDTO updateUser(CreateUserDTO userDTO, Long userId) {
        User user = userDTO.toUser();
        user.setId(userId);
        return userRepository.save(user).toUserDTO();
    }

    @Override
    public UserDTO findUserById(Long id) {
        //TODO: throw exception
        return userRepository.findById(id).orElseThrow().toUserDTO();
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow().toUserDTO();
    }
}
