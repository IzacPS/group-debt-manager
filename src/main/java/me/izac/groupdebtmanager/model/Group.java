package me.izac.groupdebtmanager.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import me.izac.groupdebtmanager.dto.GroupDto;

import java.util.List;

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

    @OneToMany(mappedBy = "group")
    private List<Debt> debts;

    public GroupDto getGroupDto(){
        return GroupDto.builder()
                .name(this.name).build();
    }
}
