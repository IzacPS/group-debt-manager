package me.izac.groupdebtmanager.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import me.izac.groupdebtmanager.model.User;

import java.util.Set;

@Getter
@Setter
@Builder
public class CreateUserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public User toUser(){
        return User.builder()
                .firstName(this.firstName)
                .lastName(this.lastName)
                .email(this.email)
                .password(this.password)
                .groups(Set.of())
                .debts(Set.of())
                .build();
    }
}
