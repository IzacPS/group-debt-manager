package me.izac.groupdebtmanager.service;

import me.izac.groupdebtmanager.dto.CreateGroupDTO;
import me.izac.groupdebtmanager.dto.CreateUserDTO;
import me.izac.groupdebtmanager.dto.GroupDTO;
import me.izac.groupdebtmanager.dto.UserDTO;
import me.izac.groupdebtmanager.model.Group;

import java.util.List;

public interface IGroupService {
    GroupDTO createGroup(CreateGroupDTO groupDto);
    List<GroupDTO> listAllGroups();

    GroupDTO updateGroup(CreateGroupDTO groupDTO, Long groupId);
    GroupDTO findGroupById(Long groupId);
}
