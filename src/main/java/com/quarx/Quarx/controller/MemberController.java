package com.quarx.Quarx.controller;

import com.quarx.Quarx.entity.Member;
import com.quarx.Quarx.service.MemberServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quarx")
public class MemberController {

    private MemberServiceImpl memberServiceImpl;

    public MemberController(MemberServiceImpl memberServiceImpl){
        this.memberServiceImpl = memberServiceImpl;
    }

    @GetMapping("/members/{id}")
    public ResponseEntity getSingleMember(@PathVariable Integer id){
        Member foundMember = memberServiceImpl.getMember(id);
        if(foundMember != null){
            return new ResponseEntity<>(foundMember, HttpStatus.OK);
        }
        return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/members")
    public List<Member> getAllMembers(){
        return memberServiceImpl.getAllMembers();
    }
    @PostMapping("/members")
    public ResponseEntity saveMember(@RequestBody Member newMember){
        if(memberServiceImpl.saveUpdate(newMember) != null){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<String>("Error",HttpStatus.BAD_REQUEST);
    }
}
