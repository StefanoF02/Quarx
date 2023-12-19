package com.quarx.Quarx.controller;

import com.quarx.Quarx.entity.Friendship;
import com.quarx.Quarx.service.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

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

    @GetMapping("/all")
    public Set<Friendship> getFriendList(@RequestParam String email){

        return friendshipService.getFriends(email);
    }

    @GetMapping("/status")
    public String getStatus(@RequestParam String ownerMail, @RequestParam String friendMail){
        return friendshipService.getFriendshipStatus(ownerMail,friendMail);
    }

    @GetMapping("/friendship")
    public Friendship getFriendship(@RequestParam String ownerMail, @RequestParam String friendMail){
        return friendshipService.findFriendship(ownerMail,friendMail);
    }

//    @DeleteMapping("/remove")
//    public ResponseEntity removeFriendship(@RequestParam String emailRequest, @RequestParam String emailReceiver){
//        return memberFriendServiceImpl.removeFriend(emailRequest,emailReceiver);
//    }
}
