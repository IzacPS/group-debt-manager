package me.izac.groupdebtmanager.model;

import jakarta.persistence.*;
import lombok.*;
import me.izac.groupdebtmanager.dto.GroupCompleteDTO;
import me.izac.groupdebtmanager.dto.GroupDTO;

import java.util.Set;

@Setter
@Getter
@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ManyToMany(mappedBy = "groups", fetch = FetchType.EAGER)
    private Set<User> users;

    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
    private Set<Debt> debts;

    public GroupDTO toGroupDTO(){
        return GroupDTO.builder()
                .id(this.id)
                .name(this.name)
                .description(this.description)
                .build();
    }

    public GroupCompleteDTO toGroupCompleteDTO(){
        return GroupCompleteDTO.builder()
                .id(this.id)
                .name(this.name)
                .description(this.description)
                .users(users.stream().map(User::getId).toList())
                .debts(debts.stream().map(Debt::getId).toList())
                .build();
    }
}
