package me.izac.groupdebtmanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import me.izac.groupdebtmanager.dto.*;
import me.izac.groupdebtmanager.service.IDebtService;
import me.izac.groupdebtmanager.service.IUserGroupService;
import me.izac.groupdebtmanager.service.IUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;
    private final IDebtService debtService;
    private final IUserGroupService userGroupService;

    @Operation(summary= "Criar um usuário")
    @PostMapping
    UserDTO createUser(@RequestBody CreateUserDTO userDTO){
        return userService.createUser(userDTO);
    }

    @Operation(summary= "Atualizar um usuário existente")
    @PutMapping("/{userId}")
    UserCompleteDTO updateUser(@RequestBody CreateUserDTO userDTO, @PathVariable Long userId){
        return userService.updateUser(userDTO, userId);
    }

    @Operation(summary= "Buscar usuário")
    @GetMapping("/{userId}")
    UserCompleteDTO getUser(@PathVariable Long userId){
        return userService.findUserById(userId);
    }

    @Operation(summary = "Listar os grupos de um usuário")
    @GetMapping("/{userId}/groups")
    List<GroupCompleteDTO> getGroupsOfUser(@PathVariable Long userId){
        return userGroupService.findAllGroupsForUser(userId);
    }

    @Operation(summary= "Listar os débitos de um usuário como devedor")
    @GetMapping("/{userId}/debts")
    List<DebtCompleteDTO> getDebtsFromUserAsDebtor(@PathVariable Long userId){
        return debtService.listAllDebtsFromDebtorById(userId);
    }

    @Operation(summary = "Marcar débitos como pago")
    @PutMapping("/{userId}/debts/pay")
    List<DebtDebtorDTO> payDebt(@RequestBody ListOfIdsDTO debts, @PathVariable Long userId){
        return debtService.payDebt(debts, userId);
    }

    @Operation(summary= "Listar os débitos de um usuário como credor")
    @GetMapping("/{userId}/credits")
    List<DebtCompleteDTO> getCreditsFromUserAsCreditor(@PathVariable Long userId){
        return debtService.listAllDebtsFromCreditorById(userId);
    }
}
