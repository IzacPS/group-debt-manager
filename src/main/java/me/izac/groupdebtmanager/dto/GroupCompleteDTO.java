package me.izac.groupdebtmanager.dto;

import lombok.*;
import me.izac.groupdebtmanager.model.Debt;
import me.izac.groupdebtmanager.model.User;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupCompleteDTO {
    private Long id;
    private String name;
    private String description;
    private List<Long> users;
    private List<Long> debts;
}
