package me.izac.groupdebtmanager.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import me.izac.groupdebtmanager.dto.GroupDTO;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@Builder
@Table(name = "tb_group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ManyToMany(mappedBy = "groups")
    private Set<User> users;

    @OneToMany(mappedBy = "group")
    private Set<Debt> debts;

    public GroupDTO toGroupDTO(){
        return GroupDTO.builder()
                .name(this.name)
                .description(this.description)
                .build();
    }
}
