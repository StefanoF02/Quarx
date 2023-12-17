package com.quarx.Quarx.entity;

import jakarta.persistence.*;

@Table(name = "member_friends")
public class MemberFriend {
    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    @Id
    @Column
    private int friendId;

    //Set friend request on pending currently not working.
    //    @Column
    //    private String status = "pending";

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }


}
