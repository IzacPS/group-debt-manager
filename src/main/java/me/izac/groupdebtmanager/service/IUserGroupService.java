package me.izac.groupdebtmanager.service;

import me.izac.groupdebtmanager.dto.*;

import java.util.List;

public interface IUserGroupService {
    List<UserCompleteDTO> findAllUsersInGroup(Long groupId);
    List<GroupCompleteDTO> findAllGroupsForUser(Long userId);
    GroupCompleteDTO addUserToGroup(ListOfIdsDTO usersId, Long groupId);
    void removeUserFromGroup(ListOfIdsDTO usersId, Long groupId);
}
