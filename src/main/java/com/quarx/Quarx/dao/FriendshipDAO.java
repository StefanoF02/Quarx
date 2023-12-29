package com.quarx.Quarx.dao;

import com.quarx.Quarx.entity.Friendship;
import com.quarx.Quarx.entity.Member;

import java.util.Optional;
import java.util.Set;

public interface FriendshipDAO {
    public Friendship findByOwnerId(Integer id);
    public Optional<Friendship> findFriendship(Integer ownerId, Member friend);
//    public void saveUpdateFriendship(Integer request, Member receiver, String date, String status);
    public void saveUpdateFriendship(Friendship friendship);
    public void removeFriendship(Friendship friendship);
    public Set<Friendship> getFriendsByOwner(Integer id);
}
