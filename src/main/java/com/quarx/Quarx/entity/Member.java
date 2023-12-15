package com.quarx.Quarx.entity;


import jakarta.persistence.*;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "firstname", nullable = false)
    private String firstname;
    @Column(name = "lastname", nullable = false)
    private String lastname;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "active")
    private Integer active = 1;


    @ManyToMany
    @JoinTable(name = "member_friends",
        joinColumns = @JoinColumn(name = "memberId") ,
        inverseJoinColumns = @JoinColumn(name = "friendId") )
    private Set<Member> friendList = new HashSet<>();


    @ManyToMany
    @JoinTable(name = "member_blocks",
        joinColumns = @JoinColumn(name = "memberId"),
        inverseJoinColumns = @JoinColumn(name = "blockId"))
    private Set<Member> blockList = new HashSet<>();

    public Member(){

    }

    public Member(String firstname, String lastname, String email, String username, String password){
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Set<Member> getFriendList() {
        return friendList;
    }

    public void setFriendList(Set<Member> friendList) {
        this.friendList = friendList;
    }


    public Set<Member> getBlockList() {
        return blockList;
    }

    public void setBlockList(Set<Member> blockList) {
        this.blockList = blockList;
    }

    public void addMemberFriend(Member member){
        if(CollectionUtils.isEmpty(this.friendList)){
            this.friendList = new HashSet<>();
        }
        this.friendList.add(member);
    }

    public void removeMemberFriend(Member member){
        if(this.friendList.contains(member)){
            this.friendList.remove(member);
        }
    }

    public void addMemberBlock(Member member){
        if(CollectionUtils.isEmpty(this.blockList)){
            this.blockList = new HashSet<>();

        }
        this.friendList.remove(member);
        this.blockList.add(member);
    }

    public void removeMemberBlock(Member member){
        if(this.blockList.contains(member)){
            this.blockList.remove(member);
        }
    }


    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", friendList=" + friendList +
                ", blockList=" + blockList +
                '}';
    }
}
