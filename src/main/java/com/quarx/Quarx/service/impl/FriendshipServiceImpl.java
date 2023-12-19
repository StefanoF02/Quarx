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
        Member friendId = memberDAO.getByEmail(emailReceiver);
        friendshipDAO.saveUpdateFriendship(request,friendId,"18.12.2023", "pending");
        return null;
    }

    @Override
    public Friendship findFriendship(String ownerMail, String friendMail) {
        Member member = memberDAO.getByEmail(ownerMail);

        Member friend = memberDAO.getByEmail(friendMail);

        return friendshipDAO.findFriendship(member.getId(),friend);
    }

    @Override
    public String getFriendshipStatus(Integer id) {
        return null;
    }

    @Override
    public String removeFriend(String emailRequest, String emailReceiver) {
        return null;
    }

    @Override
    public Set<Friendship> getFriends(String email) {
        Integer request= memberDAO.getByEmail(email).getId();

        return friendshipDAO.getFriendsByOwner(request);
    }

//    @Override
//    @Transactional
//    public ResponseEntity<Map<String, Object>> addFriend(String emailRequest, String emailReceiver) {
//        Member request = memberDAO.getByEmail(emailRequest);
//        Member receiver = memberDAO.getByEmail(emailReceiver);
//        if (emailReceiver.equals(emailRequest)) {
//            return new ResponseEntity("Email must differ", HttpStatus.BAD_REQUEST);
//        }
//        if (request.getFriendList().contains(receiver)) {
//            return new ResponseEntity("You're already friends", HttpStatus.BAD_REQUEST);
//        }
//        if(request.getBlockList().contains(receiver)){
//            return new ResponseEntity("Please remove member : " + emailReceiver + " from your block list", HttpStatus.BAD_REQUEST);
//        }
////        request.addMemberFriend(receiver);
//
//        memberDAO.saveUpdate(request);
//        MemberFriend memberFriend = memberFriendDAO.findByID(request.getId());
//
//        return new ResponseEntity("Friend added", HttpStatus.OK);
//    }
//
//    @Override
//    @Transactional
//    public ResponseEntity<Map<String, Object>> removeFriend(String emailRequest, String emailReceiver) {
//        Member request = memberDAO.getByEmail(emailRequest);
//        Member receiver = memberDAO.getByEmail(emailReceiver);
//        if (emailReceiver.equals(emailRequest)) {
//            return new ResponseEntity("You're your only friend :( ", HttpStatus.BAD_REQUEST);
//        }
//        if (!request.getFriendList().contains(receiver)) {
//            return new ResponseEntity("No friend with email: " + emailReceiver + " available", HttpStatus.BAD_REQUEST);
//        }
////        request.removeMemberFriend(receiver);
//        memberDAO.saveUpdate(request);
//        return new ResponseEntity("Removed friend", HttpStatus.OK);
//    }

}
