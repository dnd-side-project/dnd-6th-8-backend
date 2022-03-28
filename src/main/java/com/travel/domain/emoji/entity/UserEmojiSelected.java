package com.travel.domain.emoji.entity;

import com.travel.domain.archive.entity.Archives;
import com.travel.domain.common.BaseTimeEntity;
import com.travel.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class UserEmojiSelected {

    @Id
    @Column(name = "userEmojiSelectedId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "EMOJI_ID")
    private Emoji emoji;

    @ManyToOne
    @JoinColumn(name= "user_Id")
    private User user;

    @ManyToOne
    @JoinColumn(name= "archive_Id")
    private Archives archives;

    @Builder
    public UserEmojiSelected(Emoji emoji, Archives archives, User user) {
        this.archives = archives;
        this.user = user;
        setEmoji(emoji);
    }

    public void setEmoji(Emoji emoji) {
        if(this.emoji == null){
            this.emoji = emoji;
        }
    }
}