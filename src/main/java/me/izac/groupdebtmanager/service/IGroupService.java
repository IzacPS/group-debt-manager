package me.izac.groupdebtmanager.service;

import me.izac.groupdebtmanager.dto.CreateGroupDTO;
import me.izac.groupdebtmanager.dto.GroupCompleteDTO;
import me.izac.groupdebtmanager.dto.GroupDTO;

import java.util.List;

public interface IGroupService {
    GroupDTO createGroup(CreateGroupDTO groupDto);
    List<GroupCompleteDTO> listAllGroups();

    GroupCompleteDTO updateGroup(CreateGroupDTO groupDTO, Long groupId);
    GroupCompleteDTO findGroupById(Long groupId);
}
