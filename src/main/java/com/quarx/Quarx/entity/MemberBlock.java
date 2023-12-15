package com.quarx.Quarx.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class MemberBlock {

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    @Id
    @Column
    private int blockId;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public int getBlockId() {
        return blockId;
    }

    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }
}
