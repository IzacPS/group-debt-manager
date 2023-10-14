package me.izac.groupdebtmanager.model;

import jakarta.persistence.*;
import lombok.*;
import me.izac.groupdebtmanager.dto.UserCompleteDTO;
import me.izac.groupdebtmanager.dto.UserDTO;

import java.util.Set;

@Getter
@Setter
@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_group",
        joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<Group> groups;

    @OneToMany(mappedBy = "creditor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Debt> debtsAsCreditor;

    @OneToMany(mappedBy = "debtor", fetch = FetchType.EAGER)
    private Set<DebtDebtor> debtsAsDebtor;

    public UserDTO toUserDTO(){
        return UserDTO.builder()
                .id(this.id)
                .email(this.email)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .build();
    }

    public UserCompleteDTO toUserCompleteDTO(){
        return UserCompleteDTO.builder()
                .id(this.id)
                .email(this.email)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .debtsAsCreditor(debtsAsCreditor.stream().map(Debt::getId).toList())
                .debtsAsDebtor(debtsAsDebtor.stream().map(d -> d.getDebtor().getId()).toList())
                .groups(groups.stream().map(Group::getId).toList())
                .build();
    }
}
