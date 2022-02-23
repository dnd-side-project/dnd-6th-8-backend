package com.travel.domain.user.entity;

import com.travel.domain.archive.entity.Archives;
import com.travel.domain.archive.entity.EArchivingStyle;
import com.travel.domain.archive.entity.EBudget;
import com.travel.domain.emoji.entity.Emoji;
import com.travel.domain.emoji.entity.UserMarkedEmoji;
import com.travel.domain.scrap.entity.Scraps;
import com.travel.domain.sticker.entity.Sticker;
import com.travel.domain.sticker.entity.UserSticker;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
//    @Type(type = "uuid-char")
    @Column(name= "user_id", columnDefinition = "BINARY(16)")
    private String id;

    @Column()
    private String userName;

    @OneToOne(optional = true)
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @Column()
    @OneToMany(mappedBy = "user")
    private List<Archives> archives = new ArrayList<>();

//    @OneToMany(mappedBy = "user")
//    private List<UserMarkedEmoji> userMarkedEmojis = new ArrayList<>();;

//    @OneToMany(mappedBy = "user")
//    private List<Scraps> scraps = new ArrayList<>();;

    @OneToMany(mappedBy = "user")
    private List<UserSticker> userStickers = new ArrayList<>();

    @Builder
    public User(String userName) {
        this.userName = userName;
    }

}
