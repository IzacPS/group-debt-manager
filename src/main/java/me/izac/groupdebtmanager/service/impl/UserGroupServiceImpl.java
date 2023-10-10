package me.izac.groupdebtmanager.service.impl;

import lombok.RequiredArgsConstructor;
import me.izac.groupdebtmanager.dto.UserDTO;
import me.izac.groupdebtmanager.model.Group;
import me.izac.groupdebtmanager.model.User;
import me.izac.groupdebtmanager.repository.GroupRepository;
import me.izac.groupdebtmanager.repository.UserGroupRepository;
import me.izac.groupdebtmanager.repository.UserRepository;
import me.izac.groupdebtmanager.service.IUserGroupService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserGroupServiceImpl implements IUserGroupService {
    private final UserGroupRepository userGroupRepository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    @Override
    public List<UserDTO> findAllUsersInGroup(Long groupId) {
        return userGroupRepository.findAllUsersInGroup(groupId).stream().map(User::toUserDTO).toList();
    }

    @Override
    public void addUserToGroup(Long groupId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        Group group = groupRepository.findById(groupId).orElseThrow();
        group.getUsers().add(user);
        user.getGroups().add(group);

        groupRepository.save(group);
    }

    @Override
    public void removeUserFromGroup(Long groupId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        Group group = groupRepository.findById(groupId).orElseThrow();
        group.getUsers().remove(user);
        user.getGroups().remove(group);

        groupRepository.save(group);
    }
}
