package kr.ac.pusan.mysecurity.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import kr.ac.pusan.mysecurity.domain.Authority;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public class AuthorityRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(Authority authority){
        em.persist(authority);
    }

    public List<Authority> findByName(String name){
        return em.createQuery("select a from Authority as a where a.name=:name", Authority.class)
                .setParameter("name", name)
                .getResultList();
    }

    public Authority findOne(Long id){
        return em.find(Authority.class, id);
    }

    public List<Authority> findAll(){
        return em.createQuery("select a from Authority  a", Authority.class).getResultList();
    }
}
