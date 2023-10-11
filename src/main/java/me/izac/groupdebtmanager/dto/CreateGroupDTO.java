package me.izac.groupdebtmanager.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import me.izac.groupdebtmanager.model.Group;

import java.util.Set;

@Getter
@Setter
@Builder
public class CreateGroupDTO {
    private String name;
    private String description;
    public Group toGroup(){
        return Group.builder()
                .name(this.name)
                .description(this.description)
                .users(Set.of())
                .debts(Set.of())
                .build();
    }
}
