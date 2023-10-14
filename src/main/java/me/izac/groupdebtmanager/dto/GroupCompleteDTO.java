package me.izac.groupdebtmanager.dto;

import lombok.*;

import java.util.List;

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
