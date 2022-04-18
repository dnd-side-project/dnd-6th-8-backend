package com.travel.domain.emoji.entity;

import com.travel.domain.common.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Emoji {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String emoji_url;

    @Column()
    private String emoji_name;

    @Builder
    public Emoji(String emoji_name){
        this.emoji_name = emoji_name;
    }
}
