package me.izac.groupdebtmanager.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCompleteDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<Long> debtsAsCreditor;
    private List<Long> debtsAsDebtor;
    private List<Long> groups;
}
