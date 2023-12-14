package com.quarx.Quarx.dao;

import com.quarx.Quarx.entity.Member;
import jakarta.persistence.EntityManager;
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
        return entityManager.createQuery("from Member", Member.class).getResultList();
    }

    @Override
    public Member saveUpdate(Member member) {
        return entityManager.merge(member);
    }

    @Override
    public void delete(Integer id){
        Member member = entityManager.find(Member.class,id);
        entityManager.remove(member);
    }
}
