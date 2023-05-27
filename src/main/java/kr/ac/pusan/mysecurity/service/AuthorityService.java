package kr.ac.pusan.mysecurity.service;

import kr.ac.pusan.mysecurity.domain.Authority;
import kr.ac.pusan.mysecurity.domain.Member;
import kr.ac.pusan.mysecurity.repository.AuthorityRepository;
import kr.ac.pusan.mysecurity.vo.AuthorityForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthorityService {
    private final AuthorityRepository authorityRepository;
    @Transactional
        public Authority save(AuthorityForm form){
        Authority authority = new Authority();
        authority.setName(form.getName());
        authorityRepository.save(authority);
        return authority;
    }

    public List<Authority> findAll(){
        return authorityRepository.findAll();
    }

    public Authority findByName(String name){
        return authorityRepository.findByName(name).get(0);
    }

    public Authority findOne(Long AuthorityId){
        return authorityRepository.findOne(AuthorityId);
    }
}
