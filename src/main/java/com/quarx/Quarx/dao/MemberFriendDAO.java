package com.quarx.Quarx.dao;

import org.springframework.stereotype.Component;

@Component
public interface MemberFriendDAO {
    public void updateStatus(Integer id);
}
