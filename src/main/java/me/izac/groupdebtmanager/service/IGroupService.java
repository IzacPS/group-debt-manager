package me.izac.groupdebtmanager.service;

import me.izac.groupdebtmanager.dto.*;
import me.izac.groupdebtmanager.model.Group;

import java.util.List;

public interface IGroupService {
    GroupDTO createGroup(CreateGroupDTO groupDto);
    List<GroupCompleteDTO> listAllGroups();

    GroupCompleteDTO updateGroup(CreateGroupDTO groupDTO, Long groupId);
    GroupCompleteDTO findGroupById(Long groupId);
}
