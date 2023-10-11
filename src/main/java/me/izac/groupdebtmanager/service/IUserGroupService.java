package me.izac.groupdebtmanager.service;

import me.izac.groupdebtmanager.dto.GroupDTO;
import me.izac.groupdebtmanager.dto.UserDTO;

import java.util.List;

public interface IUserGroupService {
    List<UserDTO> findAllUsersInGroup(Long groupId);
    List<GroupDTO> findAllGroupsOfUser(Long userId);
    void addUserToGroup(Long groupId, Long userId);
    void removeUserFromGroup(Long groupId, Long userId);
}
