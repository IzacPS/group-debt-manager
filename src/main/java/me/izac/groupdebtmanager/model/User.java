package me.izac.groupdebtmanager.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import me.izac.groupdebtmanager.dto.UserDTO;

import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@Table(name = "tb_member")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_group",
        joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<Group> groups;

    @OneToMany(mappedBy = "debtor")
    private Set<Debt> debts;

    public UserDTO toUserDTO(){
        return UserDTO.builder()
                .email(this.email)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .build();
    }
}
