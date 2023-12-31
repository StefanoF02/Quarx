package com.quarx.Quarx.dao.impl;

import com.quarx.Quarx.dao.FriendshipDAO;
import com.quarx.Quarx.entity.Friendship;
import com.quarx.Quarx.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
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
    public Optional<Friendship> findFriendship(Integer ownerId, Member friend) {
        TypedQuery<Friendship> friendshipTypedQuery = entityManager.createQuery("FROM Friendship WHERE ownerId=:ownerId AND friend=:friend", Friendship.class);

        friendshipTypedQuery.setParameter("ownerId", ownerId);
        friendshipTypedQuery.setParameter("friend", friend);
        if((friendshipTypedQuery.getResultList().isEmpty())){
            return null;
        }

        Optional<Friendship> friendship = Optional.of(friendshipTypedQuery.getSingleResult());
        return friendship;
    }

    @Override
    @Transactional
    public void saveUpdateFriendship(Friendship newFriendship) {
        entityManager.merge(newFriendship);
    }

    @Override
    public void removeFriendship(Friendship friendship) {
        entityManager.remove(friendship);
    }


    @Override
    public Set<Friendship> getFriendsByOwner(Integer id) {
        Set<Friendship> friends = entityManager.find(Member.class, id).getFriendList();
        Set<Friendship> filterdList = new HashSet<>();
        for(Friendship filterdFriends: friends){
            if(filterdFriends.getStatus().equals("accepted")){
                filterdList.add(filterdFriends);
            }
        }
        return filterdList;
    }

}
