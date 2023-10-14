package me.izac.groupdebtmanager.dto;

import lombok.*;
import me.izac.groupdebtmanager.model.User;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
                .debtsAsCreditor(Set.of())
                .debtsAsDebtor(Set.of())
                .build();
    }
}
