package kr.ac.pusan.mysecurity.domain;

import jakarta.persistence.*;
import kr.ac.pusan.mysecurity.vo.RegMemberForm;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static kr.ac.pusan.mysecurity.domain.Algorithm.BCRYPT;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id @GeneratedValue
    @Column(name ="member_id")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private Algorithm algorithm;

    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Authority> authorities;

    private String userId;
    private String password;
    private String email;
    private String username;

    public static Member createNew(RegMemberForm form){
        return Member.builder()
                .userId(form.getUserId())
                .algorithm(BCRYPT)
                .username(form.getUsername())
                .password(form.getPassword())
                .email(form.getEmail()).build();
    }

    public void initAuthority(){
        Authority authority = new Authority("USER", this);
        authorities = new ArrayList<>();
        authorities.add(authority);
    }
}
