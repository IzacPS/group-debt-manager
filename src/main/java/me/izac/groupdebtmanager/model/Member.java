package me.izac.groupdebtmanager.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import me.izac.groupdebtmanager.dto.MemberDto;

@Getter
@Setter
@Entity
@Builder
@Table(name = "tb_member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;


    public MemberDto toMemberDto(){
        return MemberDto.builder()
                .firstName(this.firstName)
                .lastName(this.lastName)
                .password(this.password)
                .email(this.email).build();
    }
}
