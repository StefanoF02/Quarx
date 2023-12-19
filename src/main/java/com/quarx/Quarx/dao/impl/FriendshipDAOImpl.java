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
    public Friendship findByOwnerId(Integer id) {
        TypedQuery<Friendship> ownertypedQuery = entityManager.createQuery("FROM Friendship WHERE ownerId=:requestedID", Friendship.class);
        ownertypedQuery.setParameter("requestedID", id);
        Friendship friendship = ownertypedQuery.getSingleResult();
        return friendship;
    }

    @Override
    public Friendship findFriendship(Integer ownerId, Member friend) {
        TypedQuery<Friendship> friendshipTypedQuery = entityManager.createQuery("FROM Friendship WHERE ownerId=:ownerId AND friend=:friend", Friendship.class);

        friendshipTypedQuery.setParameter("ownerId", ownerId);
        friendshipTypedQuery.setParameter("friend", friend);
        Friendship friendship = friendshipTypedQuery.getSingleResult();
        return friendshipTypedQuery.getSingleResult();
    }

    @Override
    @Transactional
    public void saveUpdateFriendship(Integer ownerId, Member receiver, String date, String status) {
        Friendship newFriendship = new Friendship(ownerId,receiver,date, status);
        entityManager.persist(newFriendship);
    }

    @Override
    public String getFriendshipStatus(Integer ownerId, Integer friendId) {

        return null;
    }

    @Override
    public Set<Friendship> getFriendsByOwner(Integer id) {
//
//        TypedQuery<Member> friendshipTypedQuery = entityManager.createQuery("FROM Friendship WHERE request_id=:id", Member.class);
//
//        friendshipTypedQuery.setParameter("id", id);
//        System.out.println("Post");
//        List<Member> friends = friendshipTypedQuery.getResultList();
//        System.out.println(friends);
        return entityManager.find(Member.class, id).getFriendList();
    }

}
