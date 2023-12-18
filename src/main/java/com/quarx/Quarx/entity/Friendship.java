package com.quarx.Quarx.entity;

import jakarta.persistence.*;

@Entity
public class Friendship {
    @Id
    @Column(nullable = false)
    private Integer requesterId;


    @ManyToOne
    @JoinColumn(name = "memberId",nullable = false)
    private Member friendId;

    @Column
    private String addedAt;
    @Column
    private String status = "pending";

    public Friendship(){

    }
    public Friendship(Integer requesterId, Member friendId, String addedAt, String status) {
        this.requesterId = requesterId;
        this.friendId = friendId;
        this.addedAt = addedAt;
        this.status = status;
    }

    public Integer getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(Integer requesterId) {
        this.requesterId = requesterId;
    }

    public Member getFriendId() {
        return friendId;
    }

    public void setFriendId(Member friendId) {
        this.friendId = friendId;
    }

    public String getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(String addedAt) {
        this.addedAt = addedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Friendship{" +
                "requesterId=" + requesterId +
                ", friendId=" + friendId +
                ", addedAt='" + addedAt + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
