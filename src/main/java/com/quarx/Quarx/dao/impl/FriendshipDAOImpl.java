package com.quarx.Quarx.dao.impl;

import com.quarx.Quarx.dao.FriendshipDAO;
import com.quarx.Quarx.entity.Friendship;
import com.quarx.Quarx.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class FriendshipDAOImpl implements FriendshipDAO {

    private EntityManager entityManager;

    public FriendshipDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    @Override
    public Friendship findByID(Integer id) {
        TypedQuery<Friendship> memberFriendTypedQuery = entityManager.createQuery("FROM Member_friends WHERE requester_id=:requestedID", Friendship.class);
        memberFriendTypedQuery.setParameter("requestedID", id);
        Friendship friendship = memberFriendTypedQuery.getSingleResult();
        return friendship;
    }

    @Override
    @Transactional
    public void saveUpdateFriendship(Integer request, Member receiver, String date, String status) {
        Friendship newFriendship = new Friendship(request,receiver,date, status);
        entityManager.persist(newFriendship);
    }

    @Override
    public Set<Member> getFriends(Integer requestId) {
        TypedQuery<Friendship> memberFriendTypedQuery = entityManager.createQuery("FROM Friendship WHERE requester_id=:requestedID", Friendship.class);
        memberFriendTypedQuery.setParameter("requestedID", requestId);
        Set<Member> friends = memberFriendTypedQuery.getResultList();

    }

}
