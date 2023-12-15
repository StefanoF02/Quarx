package com.quarx.Quarx.controller;

import com.quarx.Quarx.service.impl.MemberFriendServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/friend")
public class MemberFriendController {

    private MemberFriendServiceImpl memberFriendServiceImpl;
    @Autowired
    public MemberFriendController(MemberFriendServiceImpl memberFriendServiceImpl){
        this.memberFriendServiceImpl = memberFriendServiceImpl;
    }

    @PostMapping("/add")
    public ResponseEntity addFriendship(@RequestParam String emailRequest, @RequestParam String emailReceiver){

        return memberFriendServiceImpl.addFriend(emailRequest,emailReceiver);
    }

    @DeleteMapping("/remove")
    public ResponseEntity removeFriendship(@RequestParam String emailRequest, @RequestParam String emailReceiver){
        return memberFriendServiceImpl.removeFriend(emailRequest,emailReceiver);
    }
}
