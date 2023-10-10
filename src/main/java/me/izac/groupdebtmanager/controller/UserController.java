package me.izac.groupdebtmanager.controller;

import lombok.RequiredArgsConstructor;
import me.izac.groupdebtmanager.dto.CreateUserDTO;
import me.izac.groupdebtmanager.dto.DebtDTO;
import me.izac.groupdebtmanager.dto.UserDTO;
import me.izac.groupdebtmanager.service.IDebtService;
import me.izac.groupdebtmanager.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;
    private final IDebtService debtService;

    @PostMapping
    UserDTO createUser(@RequestBody CreateUserDTO userDTO){
        return userService.createUser(userDTO);
    }

    @PutMapping("/{userId}")
    UserDTO updateUser(@RequestBody CreateUserDTO userDTO, @PathVariable Long userId){
        return userService.updateUser(userDTO, userId);
    }

    @GetMapping("/{userId}")
    UserDTO getUser(@PathVariable Long userId){
        return userService.findUserById(userId);
    }

    @GetMapping("/{userId}/debts")
    List<DebtDTO> getDebtsFromUser(@PathVariable Long userId){
        return debtService.listAllDebtsFromUser(userId);
    }
}
