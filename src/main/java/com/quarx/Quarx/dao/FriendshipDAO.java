package com.quarx.Quarx.dao;

import com.quarx.Quarx.entity.Friendship;
import com.quarx.Quarx.entity.Member;

import java.util.Set;

public interface FriendshipDAO {
    public Friendship findByID(Integer id);
    public void saveUpdateFriendship(Integer request, Member receiver, String date, String status);
    public Set<Member> getFriends(Integer requestId);
}
