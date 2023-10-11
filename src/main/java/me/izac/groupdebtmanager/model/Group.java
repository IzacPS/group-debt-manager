package me.izac.groupdebtmanager.model;

import jakarta.persistence.*;
import lombok.*;
import me.izac.groupdebtmanager.dto.GroupDTO;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
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
                .id(this.id)
                .name(this.name)
                .description(this.description)
                .build();
    }
}
