package me.izac.groupdebtmanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import me.izac.groupdebtmanager.dto.*;
import me.izac.groupdebtmanager.service.IDebtService;
import me.izac.groupdebtmanager.service.IGroupService;
import me.izac.groupdebtmanager.service.IUserGroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupController {
    private final IGroupService groupService;
    private final IUserGroupService userGroupService;
    private final IDebtService debtService;


    @Operation(summary= "Criar um novo grupo")
    @PostMapping
    GroupDTO createGroup(@RequestBody CreateGroupDTO groupDTO){
        return groupService.createGroup(groupDTO);
    }

    @Operation(summary= "Atualizar um grupo existente")
    @PutMapping("/{groupId}")
    GroupCompleteDTO updateGroup(@RequestBody CreateGroupDTO groupDTO, @PathVariable Long groupId){
        return groupService.updateGroup(groupDTO, groupId);
    }

    @Operation(summary= "Buscar um grupo")
    @GetMapping("/{groupId}")
    GroupCompleteDTO getGroup(@PathVariable Long groupId){
        return groupService.findGroupById(groupId);
    }

    @Operation(summary= "Adicionar usuários a um grupo")
    @PostMapping("/{groupId}/users")
    GroupCompleteDTO addUserToGroup(@RequestBody ListOfIdsDTO users, @PathVariable Long groupId){
        return userGroupService.addUserToGroup(users, groupId);
    }

    @Operation(summary= "Remover usuários de um grupo")
    @DeleteMapping("/{groupId}/users")
    void deleteUserToGroup(@RequestBody ListOfIdsDTO users, @PathVariable Long groupId){
        userGroupService.removeUserFromGroup(users, groupId);
    }

    @Operation(summary = "Listar todos os usuário de um grupo")
    @GetMapping("/{groupId}/users")
    List<UserCompleteDTO> getAllUsersOfGroup(@PathVariable Long groupId){
        return userGroupService.findAllUsersInGroup(groupId);
    }

    @Operation(summary= "Listar os débitos de um grupo")
    @GetMapping("/{groupId}/debts")
    List<DebtCompleteDTO> getDebtsFromGroup(@PathVariable Long groupId){
        return debtService.listAllDebtsFromGroup(groupId);
    }
}
