package com.quarx.Quarx.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface MemberFriendService {

    public ResponseEntity<Map<String, Object>> addFriend(String emailRequest, String emailReceiver);

    public ResponseEntity<Map<String, Object>> removeFriend(String emailRequest, String emailReceiver);
}
