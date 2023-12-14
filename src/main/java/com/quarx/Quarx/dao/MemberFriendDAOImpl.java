package com.quarx.Quarx.dao;


import org.springframework.stereotype.Repository;

@Repository
public class MemberFriendDAOImpl implements MemberFriendDAO{

    private MemberDAO memberDAO;

    private MemberFriendDAO memberFriendDAO;


    @Override
    public void save(Integer senderId, Integer receiverId){

    }
}
