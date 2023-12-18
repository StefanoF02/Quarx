package com.quarx.Quarx.controller;

import com.quarx.Quarx.entity.Friendship;
import com.quarx.Quarx.service.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/friend")
public class FriendshipController {

    private FriendshipService friendshipService;
    @Autowired
    public FriendshipController(FriendshipService friendshipService){
        this.friendshipService = friendshipService;
    }

    @PostMapping("/add")
    public Friendship addFriendship(@RequestParam String emailRequest, @RequestParam String emailReceiver){

        return friendshipService.addFriend(emailRequest,emailReceiver);
    }

    @GetMapping("/friends")
    public Set<Member> getFriendList(@RequestParam String email){

        return friendshipService.getFriendList(email);
    }

//    @DeleteMapping("/remove")
//    public ResponseEntity removeFriendship(@RequestParam String emailRequest, @RequestParam String emailReceiver){
//        return memberFriendServiceImpl.removeFriend(emailRequest,emailReceiver);
//    }
}
