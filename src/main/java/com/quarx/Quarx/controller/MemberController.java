package com.quarx.Quarx.controller;

import com.quarx.Quarx.entity.Member;
import com.quarx.Quarx.exception.MemberException;
import com.quarx.Quarx.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private MemberService memberService;

    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

//    @PostConstruct
//    public void createDevData(){
//        Member member1 = new Member("Stefano", "Formi","SteFor3@gmail.com","StefFor", "test123" );
//        Member member2 = new Member("Lorena", "Tre","LorTre@gmail.com","LorTre", "test123" );
//        Member member3 = new Member("Salvo", "Di","SalDi@gmail.com","SalDi", "test123" );
//        Member member4 = new Member("Fernando", "Formi","FerFor@gmail.com","FerFor", "test123" );
//        memberService.saveUpdate(member1);
//        memberService.saveUpdate(member2);
//        memberService.saveUpdate(member3);
//        memberService.saveUpdate(member4);
//
//    }

    @GetMapping("/{id}")
    public ResponseEntity getSingleMember(@PathVariable Integer id){
        Member member = memberService.getMember(id);
        if( member == null){
            throw new MemberException("Member not found");
        }
        return new ResponseEntity(member,HttpStatus.OK);
    }

    @GetMapping()
    public List<Member> getAllMembers(){
        if(memberService.getAllMembers().isEmpty()){
            throw new MemberException("No members found");
        }
        return memberService.getAllMembers();
    }

    @PostMapping()
    public ResponseEntity saveMember(@RequestBody Member newMember){
        if(memberService.saveUpdate(newMember) == null){
            throw new MemberException("Member must not be empty");
        }
        return new ResponseEntity<>("Added new member",HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMember(@PathVariable Integer id){
        if(memberService.getMember(id) == null){
            throw new MemberException("Member not found");
        }
        memberService.delete(id);
        return new ResponseEntity("Member with id: " + id + " deleted", HttpStatus.OK);
    }

}
