package me.izac.groupdebtmanager.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import me.izac.groupdebtmanager.dto.GroupDto;
import me.izac.groupdebtmanager.model.Group;
import me.izac.groupdebtmanager.repository.GroupRepository;
import me.izac.groupdebtmanager.service.IGroupService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements IGroupService {
    private final GroupRepository groupRepository;

    @Override
    public Group createMember(GroupDto groupDto) {
        Group group = Group.builder().name(groupDto.getName()).build();
        return groupRepository.save(group);
    }

    @Override
    public List<Group> listAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public Group findGroupById(Long id) {
        return groupRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Group not found"));
    }

    @Override
    public void deleteGroupById(Long id) {
        groupRepository.deleteById(id);
    }
}
