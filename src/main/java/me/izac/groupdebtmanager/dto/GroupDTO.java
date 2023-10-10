package me.izac.groupdebtmanager.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class GroupDTO {
    private Long id;
    private String name;
    private String description;
}
