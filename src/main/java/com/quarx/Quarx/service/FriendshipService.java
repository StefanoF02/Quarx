package com.quarx.Quarx.service;

import com.quarx.Quarx.entity.Friendship;
import com.quarx.Quarx.entity.Member;

import java.util.Set;

public interface FriendshipService {

    public Friendship addFriend(String emailRequest, String emailReceiver);

    public String removeFriend(String emailRequest, String emailReceiver);
    public Set<Member> getFriends(String email);
}
