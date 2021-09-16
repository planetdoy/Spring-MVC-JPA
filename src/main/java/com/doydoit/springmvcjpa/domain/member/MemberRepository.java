package com.doydoit.springmvcjpa.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class MemberRepository {

    private final EntityManager em;

    public List<Member> findByLoginId(String loginId) {
        return em.createQuery("select m from Member m where m.loginId=:loginId", Member.class)
                .setParameter("loginId", loginId).getResultList();
    }

    public void save(Member member) {
        em.persist(member);
    }
}
