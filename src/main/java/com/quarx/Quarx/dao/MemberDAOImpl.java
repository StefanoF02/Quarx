package com.quarx.Quarx.dao;

import com.quarx.Quarx.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberDAOImpl implements MemberDAO{

    private EntityManager entityManager;

    @Autowired
    public MemberDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public Member getMember(Integer id) {
        Member member = entityManager.find(Member.class, id);
        return member;
    }

    @Override
    public List<Member> getAllMembers() {
        TypedQuery<Member> typedQuery =entityManager.createQuery("from members", Member.class);
        List<Member> result = typedQuery.getResultList();
        return result;
    }

    @Override
    public Member saveUpdate(Member member) {
        return entityManager.merge(member);
    }
}
