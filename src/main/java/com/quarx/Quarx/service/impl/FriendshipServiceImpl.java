package com.quarx.Quarx.service.impl;

import com.quarx.Quarx.dao.FriendshipDAO;
import com.quarx.Quarx.dao.MemberDAO;
import com.quarx.Quarx.entity.Friendship;
import com.quarx.Quarx.entity.Member;
import com.quarx.Quarx.service.FriendshipService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    @Transactional
    public Friendship addFriend(String emailRequest, String emailReceiver) {

        Integer request= memberDAO.getByEmail(emailRequest).getId();
        Member friend= memberDAO.getByEmail(emailReceiver);
        if(friendshipDAO.findFriendship(request,friend).isEmpty()){
            Friendship newFriendship = new Friendship(request,friend,"18.12.2023", "pending" );
            friendshipDAO.saveUpdateFriendship(newFriendship);
            return newFriendship;
        }
        return friendshipDAO.findFriendship(request,friend).get();
    }



    @Override
    @Transactional
    public Friendship acceptFriend(String emailRequest, String emailReceiver) {
        Integer ownerId = memberDAO.getByEmail(emailRequest).getId();

        Member friend = memberDAO.getByEmail(emailReceiver);

        Friendship friendship = friendshipDAO.findFriendship(ownerId, friend).get();

        friendship.setStatus("accepted");
        friendshipDAO.saveUpdateFriendship(friendship);
        return friendship;
    }

    @Override
    @Transactional
    public void declineFriend(String emailRequest, String emailReceiver) {
        Integer ownerId = memberDAO.getByEmail(emailRequest).getId();

        Member friend = memberDAO.getByEmail(emailReceiver);

        Friendship friendship = friendshipDAO.findFriendship(ownerId, friend).get();

        friendshipDAO.removeFriendship(friendship);

    }

    @Override
    public Friendship findFriendship(String emailRequest, String emailReceiver) {
        Member member = memberDAO.getByEmail(emailRequest);

        Member friend = memberDAO.getByEmail(emailReceiver);

        return friendshipDAO.findFriendship(member.getId(), friend).get();
    }

    @Override
    public Set<Friendship> getFriends(String emailRequest) {
        Integer request = memberDAO.getByEmail(emailRequest).getId();

        return friendshipDAO.getFriendsByOwner(request);
    }

    @Override
    public String getFriendshipStatus(String emailRequest, String emailReceiver) {
        Integer ownerId = memberDAO.getByEmail(emailRequest).getId();

        Member friend = memberDAO.getByEmail(emailReceiver);

        Friendship friendship = friendshipDAO.findFriendship(ownerId, friend).get();
        return friendship.getStatus();
    }

    @Override
    public String removeFriend(String emailRequest, String emailReceiver) {
        Integer ownerId = memberDAO.getByEmail(emailRequest).getId();

        Member friend = memberDAO.getByEmail(emailReceiver);

        Friendship friendship = friendshipDAO.findFriendship(ownerId, friend).get();
        if (friendship.getStatus().equals("pending") || friendship == null) {
            return "Can't remove friend, she's/he's not in your friendlist";
        }
        friendshipDAO.removeFriendship(friendship);
        return "removed friend";
    }


}
