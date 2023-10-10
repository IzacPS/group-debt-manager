package me.izac.groupdebtmanager.controller;

import lombok.RequiredArgsConstructor;
import me.izac.groupdebtmanager.dto.CreateGroupDTO;
import me.izac.groupdebtmanager.dto.DebtDTO;
import me.izac.groupdebtmanager.dto.GroupDTO;
import me.izac.groupdebtmanager.service.IDebtService;
import me.izac.groupdebtmanager.service.IGroupService;
import me.izac.groupdebtmanager.service.IUserGroupService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupController {
    private final IGroupService groupService;
    private final IUserGroupService userGroupService;
    private final IDebtService debtService;
    @PostMapping
    GroupDTO createGroup(@RequestBody CreateGroupDTO groupDTO){
        return groupService.createGroup(groupDTO);
    }

    @PutMapping("/{groupId}")
    GroupDTO updateGroup(@RequestBody CreateGroupDTO groupDTO, @PathVariable Long groupId){
        return groupService.updateGroup(groupDTO, groupId);
    }

    @GetMapping("/{groupId}")
    GroupDTO getGroup(@PathVariable Long groupId){
        return groupService.findGroupById(groupId);
    }

    @PostMapping("/{groupId}/users/{userId}")
    void addUserToGroup(@PathVariable Long groupId, @PathVariable Long userId){
        userGroupService.addUserToGroup(groupId, userId);
    }

    @DeleteMapping("/{groupId}/users/{userId}")
    void deleteUserToGroup(@PathVariable Long groupId, @PathVariable Long userId){
        userGroupService.removeUserFromGroup(groupId, userId);
    }

    @GetMapping("/{groupId}/debts")
    List<DebtDTO> getDebtsFromGroup(@PathVariable Long groupId){
        return debtService.listAllDebtsFromGroup(groupId);
    }
}
