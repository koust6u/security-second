package kr.ac.pusan.mysecurity.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Authority {
    @Id
    @GeneratedValue
    @Column(name="authority_id")
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public Authority(String name, Member member){
        this.name = name;
        this.member = member;
    }
}
