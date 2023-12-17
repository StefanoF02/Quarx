package com.quarx.Quarx.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "conversations")
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String title;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member creator;

    @OneToMany(mappedBy = "conversation")
    private Set<Message> messages;
}
