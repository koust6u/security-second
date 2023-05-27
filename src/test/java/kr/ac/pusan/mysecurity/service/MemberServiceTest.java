package kr.ac.pusan.mysecurity.service;

import kr.ac.pusan.mysecurity.domain.Algorithm;
import kr.ac.pusan.mysecurity.domain.Member;
import org.aspectj.lang.annotation.RequiredTypes;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static kr.ac.pusan.mysecurity.domain.Algorithm.BCRYPT;
import static kr.ac.pusan.mysecurity.domain.Algorithm.SCRYPT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@Transactional
@SpringBootTest
class MemberServiceTest {

    @Autowired private MemberService memberService;

    @Test
    @Rollback(false)
    void signUp() {
        //given
        Member member = Member.builder()
                .username("kim")
                .email("godminjong@naver.com")
                .userId("koust6u")
                .algorithm(BCRYPT)
                .password("korea13")
                .build();
        //when
        memberService.signUp(member);
        //then
        Member findMember = memberService.findByUserId(member.getUserId()).get(0);
        assertThat(member).isEqualTo(findMember);
        assertThat(member.getUserId()).isEqualTo(findMember.getUserId());
        assertThat(member.getPassword()).isEqualTo(findMember.getPassword());
        assertThat(member.getEmail()).isEqualTo(findMember.getEmail());
        assertThat(member.getAlgorithm()).isEqualTo(findMember.getAlgorithm());
    }
    
    @Test
    @Transactional
    public void checkDuplicated() throws Exception{
        //given
        Member first = Member.builder()
                .username("lee")
                .password("korea")
                .userId("kosut6u")
                .algorithm(SCRYPT)
                .build();
        Member second = Member.builder()
                .username("park")
                .password("korea")
                .userId("kosut6u")
                .algorithm(BCRYPT)
                .build();
        //when
        memberService.signUp(first);
        //then
        assertThrows(IllegalStateException.class, ()->{
            memberService.signUp(second);
        });
     }

}