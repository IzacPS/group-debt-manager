package me.izac.groupdebtmanager.controller;

import io.swagger.v3.oas.annotations.Operation;
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


    @Operation(summary= "Cria um novo grupo")
    @PostMapping
    GroupDTO createGroup(@RequestBody CreateGroupDTO groupDTO){
        return groupService.createGroup(groupDTO);
    }

    @Operation(summary= "Atualizar um grupo existente")
    @PutMapping("/{groupId}")
    GroupDTO updateGroup(@RequestBody CreateGroupDTO groupDTO, @PathVariable Long groupId){
        return groupService.updateGroup(groupDTO, groupId);
    }

    @Operation(summary= "Buscar um grupo")
    @GetMapping("/{groupId}")
    GroupDTO getGroup(@PathVariable Long groupId){
        return groupService.findGroupById(groupId);
    }

    @Operation(summary= "Adicionar um usuário a um grupo")
    @PostMapping("/{groupId}/users/{userId}")
    void addUserToGroup(@PathVariable Long groupId, @PathVariable Long userId){
        userGroupService.addUserToGroup(groupId, userId);
    }

    @Operation(summary= "Remover um usuário de um grupo")
    @DeleteMapping("/{groupId}/users/{userId}")
    void deleteUserToGroup(@PathVariable Long groupId, @PathVariable Long userId){
        userGroupService.removeUserFromGroup(groupId, userId);
    }

    @Operation(summary= "Listar os debtos de um grupo")
    @GetMapping("/{groupId}/debts")
    List<DebtDTO> getDebtsFromGroup(@PathVariable Long groupId){
        return debtService.listAllDebtsFromGroup(groupId);
    }
}
