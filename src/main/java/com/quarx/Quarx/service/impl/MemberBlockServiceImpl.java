package com.quarx.Quarx.service.impl;

import com.quarx.Quarx.dao.MemberDAO;
import com.quarx.Quarx.entity.Member;
import com.quarx.Quarx.service.MemberBlockService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MemberBlockServiceImpl implements MemberBlockService {
    private MemberDAO memberDAO;

    @Autowired
    public MemberBlockServiceImpl(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    @Override
    @Transactional
    public ResponseEntity<Map<String, Object>> addBlock(String emailRequest, String emailReceiver) {
        Member request = memberDAO.getByEmail(emailRequest);
        Member receiver = memberDAO.getByEmail(emailReceiver);
        if (emailReceiver.equals(emailRequest)) {
            return new ResponseEntity("You can't block yourself", HttpStatus.BAD_REQUEST);
        }
        if (request.getBlockList().contains(receiver)) {
            return new ResponseEntity("You're blocked this member", HttpStatus.NOT_FOUND);
        }
        request.addMemberBlock(receiver);

        memberDAO.saveUpdate(request);
        return new ResponseEntity(emailReceiver + " got blocked", HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<Map<String, Object>> removeBlock(String emailRequest, String emailReceiver) {
        Member request = memberDAO.getByEmail(emailRequest);
        Member receiver = memberDAO.getByEmail(emailReceiver);
        if (emailReceiver.equals(emailRequest)) {
            return new ResponseEntity("You can't unblock yourself", HttpStatus.BAD_REQUEST);
        }
        if (!request.getBlockList().contains(receiver)) {
            return new ResponseEntity("No Member with email: " + emailReceiver + " available", HttpStatus.NOT_FOUND);
        }
        request.removeMemberBlock(receiver);
        memberDAO.saveUpdate(request);
        return new ResponseEntity("Blocked member", HttpStatus.OK);
    }
}
