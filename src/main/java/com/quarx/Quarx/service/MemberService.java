package com.quarx.Quarx.service;

import com.quarx.Quarx.entity.Member;

import java.util.List;


public interface MemberService {

    public Member getMember(Integer id);

    public Member getByEmail(String email);

    public List<Member> getAllMembers();

    public Member saveUpdate(Member member);

    public void delete(Integer id);

//    public Set<Member> getFriendList(String email);

}
