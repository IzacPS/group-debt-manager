package me.izac.groupdebtmanager.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import me.izac.groupdebtmanager.dto.CreateGroupDTO;
import me.izac.groupdebtmanager.dto.GroupDTO;
import me.izac.groupdebtmanager.dto.UserDTO;
import me.izac.groupdebtmanager.model.Group;
import me.izac.groupdebtmanager.model.User;
import me.izac.groupdebtmanager.repository.GroupRepository;
import me.izac.groupdebtmanager.service.IGroupService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements IGroupService {
    private final GroupRepository groupRepository;

    @Override
    public GroupDTO createGroup(CreateGroupDTO groupDto) {
        return groupRepository.save(groupDto.toGroup()).toGroupDTO();
    }

    @Override
    public List<GroupDTO> listAllGroups() {
        return groupRepository.findAll().stream().map(Group::toGroupDTO).toList();
    }

    @Override
    public GroupDTO updateGroup(CreateGroupDTO groupDTO, Long groupId) {
        Group group = groupDTO.toGroup();
        group.setId(groupId);
        return groupRepository.save(group).toGroupDTO();
    }

    @Override
    public GroupDTO findGroupById(Long groupId) {
        return groupRepository.findById(groupId).orElseThrow().toGroupDTO();
    }
}
