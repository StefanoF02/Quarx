package com.quarx.Quarx.dao;

import com.quarx.Quarx.entity.Member;

import java.util.List;

public interface MemberDAO {

    public Member getMember(Integer id);

    public List<Member> getAllMembers();

    public Member saveUpdate(Member member);

    public void delete(Integer id);

}
