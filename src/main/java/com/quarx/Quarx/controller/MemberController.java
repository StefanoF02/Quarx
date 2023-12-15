package com.quarx.Quarx.controller;

import com.quarx.Quarx.entity.Member;
import com.quarx.Quarx.exception.MemberException;
import com.quarx.Quarx.service.impl.MemberServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/members")
public class MemberController {

    private MemberServiceImpl memberServiceImpl;

    public MemberController(MemberServiceImpl memberServiceImpl){
        this.memberServiceImpl = memberServiceImpl;
    }

    @PostConstruct
    public void createDevData(){
        Member member1 = new Member("Stefano", "Formi","SteFor3@gmail.com","StefFor", "test123" );
        Member member2 = new Member("Lorena", "Tre","LorTre@gmail.com","LorTre", "test123" );
        Member member3 = new Member("Salvo", "Di","SalDi@gmail.com","SalDi", "test123" );
        Member member4 = new Member("Fernando", "Formi","FerFor@gmail.com","FerFor", "test123" );
        memberServiceImpl.saveUpdate(member1);
        memberServiceImpl.saveUpdate(member2);
        memberServiceImpl.saveUpdate(member3);
        memberServiceImpl.saveUpdate(member4);

    }

    @GetMapping("/{id}")
    public ResponseEntity getSingleMember(@PathVariable Integer id){
        Member member = memberServiceImpl.getMember(id);
        if( member == null){
            throw new MemberException("Member not found");
        }
        return new ResponseEntity(member,HttpStatus.OK);
    }

    @GetMapping()
    public List<Member> getAllMembers(){
        if(memberServiceImpl.getAllMembers().isEmpty()){
            throw new MemberException("No members found");
        }
        return memberServiceImpl.getAllMembers();
    }

    @PostMapping()
    public ResponseEntity saveMember(@RequestBody Member newMember){
        if(memberServiceImpl.saveUpdate(newMember) == null){
            throw new MemberException("Member must not be empty");
        }
        return new ResponseEntity<>("Added new member",HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMember(@PathVariable Integer id){
        if(memberServiceImpl.getMember(id) == null){
            throw new MemberException("Member not found");
        }
        memberServiceImpl.delete(id);
        return new ResponseEntity("Member with id: " + id + " deleted", HttpStatus.OK);
    }

    @GetMapping("/friends")
    public Set<Member> getFriendList(@RequestParam String email){

        return memberServiceImpl.getFriendList(email);
    }

}
