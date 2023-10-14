package me.izac.groupdebtmanager.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import me.izac.groupdebtmanager.dto.CreateGroupDTO;
import me.izac.groupdebtmanager.dto.GroupCompleteDTO;
import me.izac.groupdebtmanager.dto.GroupDTO;
import me.izac.groupdebtmanager.dto.UserDTO;
import me.izac.groupdebtmanager.model.Debt;
import me.izac.groupdebtmanager.model.Group;
import me.izac.groupdebtmanager.model.User;
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
    private final DebtRepository debtRepository;
    private final UserRepository userRepository;

    @Override
    public GroupDTO createGroup(CreateGroupDTO groupDto) {
        return groupRepository.save(groupDto.toGroup()).toGroupDTO();
    }

    @Override
    public List<GroupCompleteDTO> listAllGroups() {
        //            List<Long> users = userRepository.findAllByGroupId(group.getId()).stream().map(User::getId).toList();
        //            List<Long> debts = debtRepository.findAllByGroupId(group.getId()).stream().map(Debt::getId).toList();
        return groupRepository.findAll().stream().map(Group::toGroupCompleteDTO).toList();
    }

    @Override
    public GroupCompleteDTO updateGroup(CreateGroupDTO groupDTO, Long groupId) {

        Group group = groupDTO.toGroup();
        group.setId(groupId);
//        List<Long> users = userRepository.findAllByGroupId(groupId).stream().map(User::getId).toList();
//        List<Long> debts = debtRepository.findAllByGroupId(groupId).stream().map(Debt::getId).toList();
        return groupRepository.save(group).toGroupCompleteDTO();
    }

    @Override
    public GroupCompleteDTO findGroupById(Long groupId) {
//        List<Long> users = userRepository.findAllByGroupId(groupId).stream().map(User::getId).toList();
//        List<Long> debts = debtRepository.findAllByGroupId(groupId).stream().map(Debt::getId).toList();
        return groupRepository.findById(groupId).orElseThrow().toGroupCompleteDTO();
    }
}
