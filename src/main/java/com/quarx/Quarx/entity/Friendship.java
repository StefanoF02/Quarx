package com.quarx.Quarx.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Friendship {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer friendshipId;

    @Column
    @JsonIgnore
    private Integer ownerId;
    @ManyToOne
    @JoinColumn(name = "memberId",nullable = false)
    private Member friend;

    @Column
    private String addedAt;
    @Column
    private String status = "pending";

    public Friendship(){

    }
    public Friendship(Integer ownerId, Member friend, String addedAt, String status) {
        this.ownerId = ownerId;
        this.friend = friend;
        this.addedAt = addedAt;
        this.status = status;
    }
    public Integer getFriendshipId(){return friendshipId;}

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Member getFriendId() {
        return friend;
    }

    public void setFriendId(Member friendId) {
        this.friend = friendId;
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
                "friendship_id=" + friendshipId +
                ", ownerId=" + ownerId +
                ", friend=" + friend +
                ", addedAt='" + addedAt + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
