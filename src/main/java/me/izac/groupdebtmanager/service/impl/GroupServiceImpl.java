package me.izac.groupdebtmanager.service.impl;

import lombok.RequiredArgsConstructor;
import me.izac.groupdebtmanager.dto.CreateGroupDTO;
import me.izac.groupdebtmanager.dto.GroupCompleteDTO;
import me.izac.groupdebtmanager.dto.GroupDTO;
import me.izac.groupdebtmanager.exception.GroupNotFound;
import me.izac.groupdebtmanager.model.Group;
import me.izac.groupdebtmanager.repository.DebtRepository;
import me.izac.groupdebtmanager.repository.GroupRepository;
import me.izac.groupdebtmanager.repository.UserRepository;
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
    public List<GroupCompleteDTO> listAllGroups() {
        return groupRepository.findAll().stream().map(Group::toGroupCompleteDTO).toList();
    }

    @Override
    public GroupCompleteDTO updateGroup(CreateGroupDTO groupDTO, Long groupId) {

        Group group = groupDTO.toGroup();
        group.setId(groupId);
        return groupRepository.save(group).toGroupCompleteDTO();
    }

    @Override
    public GroupCompleteDTO findGroupById(Long groupId) {
        return groupRepository.findById(groupId).orElseThrow(() -> new GroupNotFound(groupId)).toGroupCompleteDTO();
    }
}
