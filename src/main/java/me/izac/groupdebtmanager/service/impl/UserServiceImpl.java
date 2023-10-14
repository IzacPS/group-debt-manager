package me.izac.groupdebtmanager.service.impl;

import lombok.RequiredArgsConstructor;
import me.izac.groupdebtmanager.dto.CreateUserDTO;
import me.izac.groupdebtmanager.dto.UserCompleteDTO;
import me.izac.groupdebtmanager.dto.UserDTO;
import me.izac.groupdebtmanager.exception.UserNotFound;
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
    public UserCompleteDTO updateUser(CreateUserDTO userDTO, Long userId) {
        User user = userDTO.toUser();
        user.setId(userId);
        return userRepository.save(user).toUserCompleteDTO();
    }

    @Override
    public UserCompleteDTO findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFound(id))
                .toUserCompleteDTO();
    }

    @Override
    public UserCompleteDTO findUserByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFound(email))
                .toUserCompleteDTO();
    }
}
