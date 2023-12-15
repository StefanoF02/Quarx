package com.quarx.Quarx.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface MemberBlockService {
    public ResponseEntity<Map<String, Object>> addBlock(String emailRequest, String emailReceiver);

    public ResponseEntity<Map<String, Object>> removeBlock(String emailRequest, String emailReceiver);
}
