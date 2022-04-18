package com.travel.domain.emoji.entity;

import com.fasterxml.jackson.databind.ser.Serializers;
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
public class UserEmojiSelected extends BaseTimeEntity {

    @Id
    @Column(name = "USER_EMOJI_SELECTED_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "EMOJI_ID")
    private Emoji emoji;

    @ManyToOne
    @JoinColumn(name= "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name= "ARCHIVE_ID")
    private Archives archives;

    @Builder
    public UserEmojiSelected(Emoji emoji, Archives archives, User user) {
        this.archives = archives;
        this.user = user;
        this.emoji = emoji;
    }
}