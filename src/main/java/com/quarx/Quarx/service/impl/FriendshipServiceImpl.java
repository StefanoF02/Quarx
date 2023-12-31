package com.quarx.Quarx.service.impl;

import com.quarx.Quarx.dao.FriendshipDAO;
import com.quarx.Quarx.dao.MemberDAO;
import com.quarx.Quarx.entity.Friendship;
import com.quarx.Quarx.entity.Member;
import com.quarx.Quarx.exception.FriendshipException;
import com.quarx.Quarx.service.FriendshipService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@Service
public class FriendshipServiceImpl implements FriendshipService {

    private MemberDAO memberDAO;
    private FriendshipDAO friendshipDAO;

    @Autowired
    public FriendshipServiceImpl(MemberDAO memberDAO, FriendshipDAO friendshipDAO) {
        this.memberDAO = memberDAO;
        this.friendshipDAO = friendshipDAO;
    }

    //This function is used to add a member to the friendList in member
    //The request is set to on pending by default
    @Override
    @Transactional
    public Friendship addFriend(String emailRequest, String emailReceiver) {

        Integer request = memberDAO.getByEmail(emailRequest).getId();
        Member friend = memberDAO.getByEmail(emailReceiver);
        if (friendshipDAO.findFriendship(request, friend) == null) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            Friendship newFriendship = new Friendship(request, friend, dateFormat.format(date), "pending");
            friendshipDAO.saveUpdateFriendship(newFriendship);
            return newFriendship;
        }
        throw new IllegalArgumentException("Friendship already exists");

    }


    //This function is used to change the status attribute of the member from pending to accepted
    @Override
    @Transactional
    public Friendship acceptFriend(String emailRequest, String emailReceiver) {
        Integer ownerId = memberDAO.getByEmail(emailRequest).getId();

        Member friend = memberDAO.getByEmail(emailReceiver);

        Friendship friendship = friendshipDAO.findFriendship(ownerId, friend).get();
        if (friendship.getStatus().equals("accepted")) {
            throw new IllegalArgumentException("Friendship already accepted");
        }
        friendship.setStatus("accepted");
        friendshipDAO.saveUpdateFriendship(friendship);
        return friendship;
    }

    //This function is used to change the status attribute of the member from pending to decline.
    //The status won't be set to decline, the friendship object will be removed from the DB.
    @Override
    @Transactional
    public void declineFriend(String emailRequest, String emailReceiver) {
        Integer ownerId = memberDAO.getByEmail(emailRequest).getId();

        Member friend = memberDAO.getByEmail(emailReceiver);

        Friendship friendship = friendshipDAO.findFriendship(ownerId, friend).get();

        friendshipDAO.removeFriendship(friendship);

    }

    //The findFriendship method is used to find a friendship object from the DB.
    @Override
    public Friendship findFriendship(String emailRequest, String emailReceiver) {
        Member member = memberDAO.getByEmail(emailRequest);

        Member friend = memberDAO.getByEmail(emailReceiver);
        if (friendshipDAO.findFriendship(member.getId(), friend).isEmpty()) {
            return null;
        }
        return friendshipDAO.findFriendship(member.getId(), friend).get();
    }

    //Get all friends in a friendlist by the requester
    @Override
    public Set<Friendship> getFriends(String emailRequest) {
        Integer request = memberDAO.getByEmail(emailRequest).getId();
        if(friendshipDAO.getFriendsByOwner(request).isEmpty()){
            throw new FriendshipException("No friends found");
        }
        return friendshipDAO.getFriendsByOwner(request);
    }

    //Get the status of a friendship object in DB
    @Override
    public String getFriendshipStatus(String emailRequest, String emailReceiver) {
        Integer ownerId = memberDAO.getByEmail(emailRequest).getId();

        Member friend = memberDAO.getByEmail(emailReceiver);
        if (friendshipDAO.findFriendship(ownerId, friend) == null) {
            throw new FriendshipException("No friendship existing");
        }
        return friendshipDAO.findFriendship(ownerId, friend).get().getStatus();
    }

    //Remove a friendship object from the DB.
    @Override
    @Transactional
    public String removeFriend(String emailRequest, String emailReceiver) {
        Integer ownerId = memberDAO.getByEmail(emailRequest).getId();

        Member friend = memberDAO.getByEmail(emailReceiver);

        Friendship friendship = friendshipDAO.findFriendship(ownerId, friend).get();
        if (friendship == null) {
            throw new IllegalArgumentException("Can't remove friend, she's/he's not in your friendlist");
        }
        if (friendship.getStatus().equals("pending")) {
            friendshipDAO.removeFriendship(friendship);
            return "Request revoked";
        }
        friendshipDAO.removeFriendship(friendship);
        return "removed friend";
    }


}
