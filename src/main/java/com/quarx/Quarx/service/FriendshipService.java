package com.quarx.Quarx.service;

import com.quarx.Quarx.entity.Friendship;

import java.util.Set;

public interface FriendshipService {

    public Friendship addFriend(String emailRequest, String emailReceiver);
    public Friendship findFriendship(String ownerMail, String friendMail);
    public String getFriendshipStatus(String ownerMail, String friendMail);
    public String removeFriend(String emailRequest, String emailReceiver);
    public Set<Friendship> getFriends(String email);
}
