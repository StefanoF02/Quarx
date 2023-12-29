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
    @PostMapping("/accept")
    public String acceptFriend(@RequestParam String emailRequest, @RequestParam String emailReceiver){
        Friendship acceptedFriendship = friendshipService.acceptFriend(emailRequest,emailReceiver);
        if(acceptedFriendship.getStatus().equals("accepted")){
            return "Accepted a new friendship sir";
        }
        return "Error";
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

    @DeleteMapping("/decline")
    public void declineFriendship(@RequestParam String emailRequest, @RequestParam String emailReceiver){
        friendshipService.declineFriend(emailRequest,emailReceiver);
    }

    @DeleteMapping("/remove")
    public String removeFriend(@RequestParam String emailRequest, @RequestParam String emailReceiver){
        return friendshipService.removeFriend(emailRequest,emailReceiver);
    }
}
