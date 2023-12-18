package com.quarx.Quarx.service.impl;

import com.quarx.Quarx.dao.MemberDAO;
import com.quarx.Quarx.entity.Member;
import com.quarx.Quarx.service.MemberService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private MemberDAO memberDAO;

    public MemberServiceImpl(MemberDAO memberDAO){
        this.memberDAO = memberDAO;
    }

    @Override
    public Member getMember(Integer id) {
        Member member = memberDAO.getMember(id);
        if(member == null){
            return null;
        }
        return member;
    }

    @Override
    public Member getByEmail(String email) {
        Member member = memberDAO.getByEmail(email);
        return member;
    }

    @Override
    public List<Member> getAllMembers() {
        return memberDAO.getAllMembers();
    }

    @Override
    @Transactional
    public Member saveUpdate(Member member) {
       return memberDAO.saveUpdate(member);
    }

    @Override
    @Transactional
    public void delete(Integer id){
        memberDAO.delete(id);
    }

}
