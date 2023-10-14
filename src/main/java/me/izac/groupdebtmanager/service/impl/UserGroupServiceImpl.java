package me.izac.groupdebtmanager.service.impl;

import lombok.RequiredArgsConstructor;
import me.izac.groupdebtmanager.dto.GroupCompleteDTO;
import me.izac.groupdebtmanager.dto.ListOfIdsDTO;
import me.izac.groupdebtmanager.dto.UserCompleteDTO;
import me.izac.groupdebtmanager.model.Group;
import me.izac.groupdebtmanager.model.User;
import me.izac.groupdebtmanager.repository.DebtRepository;
import me.izac.groupdebtmanager.repository.GroupRepository;
import me.izac.groupdebtmanager.repository.UserRepository;
import me.izac.groupdebtmanager.service.IUserGroupService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserGroupServiceImpl implements IUserGroupService {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final DebtRepository debtRepository;
    @Override
    public List<UserCompleteDTO> findAllUsersInGroup(Long groupId) {
        //            List<Long> debtsAsCreditor = debtRepository.findAllByCreditorId(user.getId()).stream().map(Debt::getId).toList();
        //            List<Long> debtsAsDebtor = debtRepository.findAllByDebtorsId(user.getId()).stream().map(Debt::getId).toList();
        //            List<Long> groups = groupRepository.findAllByUserId(user.getId()).stream().map(Group::getId).toList();
        return userRepository.findAllByGroupId(groupId).stream().map(User::toUserCompleteDTO).toList();
    }

    @Override
    public List<GroupCompleteDTO> findAllGroupsForUser(Long userId) {
        //            List<Long> debts = debtRepository.findAllByGroupId(group.getId()).stream().map(Debt::getId).toList();
        //            List<Long> users = userRepository.findAllByGroupId(group.getId()).stream().map(User::getId).toList();
        return groupRepository.findAllByUserId(userId).stream().map(Group::toGroupCompleteDTO).toList();
    }

    @Override
    public GroupCompleteDTO addUserToGroup(ListOfIdsDTO usersId, Long groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow();

        for(Long i : usersId.getIds()){
            User u = userRepository.findById(i).orElseThrow();
            u.getGroups().add(group);
            group.getUsers().add(u);
        }

        group  = groupRepository.save(group);
//        List<Long> debts = debtRepository.findAllByGroupId(groupId).stream().map(Debt::getId).toList();
//        List<Long> users = userRepository.findAllByGroupId(groupId).stream().map(User::getId).toList();
        return group.toGroupCompleteDTO();
    }

    @Override
    public void removeUserFromGroup(ListOfIdsDTO usersId, Long groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow();


        for(Long i : usersId.getIds()){
            User u = userRepository.findById(i).orElseThrow();
            u.getGroups().remove(group);
            //group.getUsers().remove(u);
        }

        groupRepository.save(group);
    }
}
