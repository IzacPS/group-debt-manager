package me.izac.groupdebtmanager.service.impl;

import lombok.RequiredArgsConstructor;
import me.izac.groupdebtmanager.dto.GroupCompleteDTO;
import me.izac.groupdebtmanager.dto.ListOfIdsDTO;
import me.izac.groupdebtmanager.dto.UserCompleteDTO;
import me.izac.groupdebtmanager.exception.GroupNotFound;
import me.izac.groupdebtmanager.exception.UserNotFound;
import me.izac.groupdebtmanager.model.Group;
import me.izac.groupdebtmanager.model.User;
import me.izac.groupdebtmanager.repository.DebtRepository;
import me.izac.groupdebtmanager.repository.GroupRepository;
import me.izac.groupdebtmanager.repository.UserRepository;
import me.izac.groupdebtmanager.service.IUserGroupService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserGroupServiceImpl implements IUserGroupService {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    @Override
    public List<UserCompleteDTO> findAllUsersInGroup(Long groupId) {
        return userRepository.findAllByGroupId(groupId).stream().map(User::toUserCompleteDTO).toList();
    }

    @Override
    public List<GroupCompleteDTO> findAllGroupsForUser(Long userId) {
        return groupRepository.findAllByUserId(userId).stream().map(Group::toGroupCompleteDTO).toList();
    }

    @Override
    public GroupCompleteDTO addUserToGroup(ListOfIdsDTO usersId, Long groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new GroupNotFound(groupId));

        for(Long i : usersId.getIds()){
            User u = userRepository.findById(i).orElseThrow(() -> new UserNotFound(i));
            u.getGroups().add(group);
            group.getUsers().add(u);
        }

        group  = groupRepository.save(group);
        return group.toGroupCompleteDTO();
    }

    @Override
    public void removeUserFromGroup(ListOfIdsDTO usersId, Long groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new GroupNotFound(groupId));


        for(Long i : usersId.getIds()){
            User u = userRepository.findById(i).orElseThrow(() -> new UserNotFound(i));
            u.getGroups().remove(group);
        }

        groupRepository.save(group);
    }
}
