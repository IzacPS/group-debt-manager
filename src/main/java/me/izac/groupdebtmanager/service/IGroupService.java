package me.izac.groupdebtmanager.service;

import me.izac.groupdebtmanager.dto.GroupDto;
import me.izac.groupdebtmanager.model.Group;

import java.util.List;

public interface IGroupService {
    Group createMember(GroupDto groupDto);
    List<Group> listAllGroups();
    Group findGroupById(Long groupId);
    void deleteGroupById(Long groupId);
}
