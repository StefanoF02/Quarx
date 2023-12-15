package com.quarx.Quarx.service.impl;

import com.quarx.Quarx.dao.MemberDAO;
import com.quarx.Quarx.entity.Member;
import com.quarx.Quarx.service.MemberFriendService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MemberFriendServiceImpl implements MemberFriendService {

    private MemberDAO memberDAO;

    @Autowired
    public MemberFriendServiceImpl(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    @Override
    @Transactional
    public ResponseEntity<Map<String, Object>> addFriend(String emailRequest, String emailReceiver) {
        Member request = memberDAO.getByEmail(emailRequest);
        Member receiver = memberDAO.getByEmail(emailReceiver);
        if (emailReceiver.equals(emailRequest)) {
            return new ResponseEntity("Email must differ", HttpStatus.BAD_REQUEST);
        }
        if (request.getFriendList().contains(receiver)) {
            return new ResponseEntity("You're already friends", HttpStatus.BAD_REQUEST);
        }
        if(request.getBlockList().contains(receiver)){
            return new ResponseEntity("Please remove member : " + emailReceiver + " from your block list", HttpStatus.NOT_FOUND);
        }
        request.addMemberFriend(receiver);

        memberDAO.saveUpdate(request);
        return new ResponseEntity("Friend added", HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<Map<String, Object>> removeFriend(String emailRequest, String emailReceiver) {
        Member request = memberDAO.getByEmail(emailRequest);
        Member receiver = memberDAO.getByEmail(emailReceiver);
        if (emailReceiver.equals(emailRequest)) {
            return new ResponseEntity("You're your only friend :( ", HttpStatus.BAD_REQUEST);
        }
        if (!request.getFriendList().contains(receiver)) {
            return new ResponseEntity("No friend with email: " + emailReceiver + " available", HttpStatus.BAD_REQUEST);
        }
        request.removeMemberFriend(receiver);
        memberDAO.saveUpdate(request);
        return new ResponseEntity("Removed friend", HttpStatus.OK);
    }

}
