package com.quarx.Quarx.controller;

import com.quarx.Quarx.service.impl.MemberBlockServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/block")
public class MemberBlockController {

    private MemberBlockServiceImpl memberBlockServiceImpl;
    @Autowired
    public MemberBlockController(MemberBlockServiceImpl memberBlockServiceImpl){
        this.memberBlockServiceImpl = memberBlockServiceImpl;
    }

    @PostMapping("/add")
    public ResponseEntity addBlock(@RequestParam String emailRequest, @RequestParam String emailReceiver){

        return memberBlockServiceImpl.addBlock(emailRequest,emailReceiver);
    }

    @DeleteMapping("/remove")
    public ResponseEntity removeBlock(@RequestParam String emailRequest, @RequestParam String emailReceiver){
        return memberBlockServiceImpl.removeBlock(emailRequest,emailReceiver);
    }
}
