package kr.ac.pusan.mysecurity.service;

import kr.ac.pusan.mysecurity.domain.Member;
import kr.ac.pusan.mysecurity.repository.AuthorityRepository;
import kr.ac.pusan.mysecurity.repository.MemberRepository;
import kr.ac.pusan.mysecurity.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;


    @Transactional
    public Member signUp(Member member){
        checkDuplicated(member.getUserId());
        memberRepository.save(member);
        return member;
    }


    public List<Member> findByUserId(String userId){
        return memberRepository.findByUserId(userId);
    }

    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    public Member findOne(Long id){
        return memberRepository.findOne(id);
    }


    private void checkDuplicated(String userId) {
        List<Member> findMember = memberRepository.findByUserId(userId);
        if(!findMember.isEmpty()) throw new IllegalStateException("Duplicated!!!");
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member= memberRepository.findByUserId(username).get(0);
        return new CustomUserDetails(member);
    }
}
