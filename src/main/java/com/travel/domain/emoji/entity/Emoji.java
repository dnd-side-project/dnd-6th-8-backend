package com.travel.domain.emoji.entity;

import lombok.Builder;

import javax.persistence.*;

@Entity
public class Emoji {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String emoji_url;

    @Column()
    private String emoji_name;
}
