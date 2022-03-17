package com.travel.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.travel.domain.archive.entity.Archives;
import com.travel.domain.archive.entity.EArchivingStyle;
import com.travel.domain.archive.entity.EBudget;
import com.travel.domain.emoji.entity.UserMarkedEmoji;
import com.travel.domain.scrap.entity.Scraps;
import com.travel.domain.sticker.entity.Sticker;
import com.travel.domain.sticker.entity.UserStickerSelected;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column()
    private String userName;

    @Column()
    private String email;

    @Column()
    private String profilePicture;

    @OneToOne(optional = true)
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @Column()
    @OneToMany(mappedBy = "user")
    private List<Archives> archives = new ArrayList<>();

//    @OneToMany(mappedBy = "user")
//    private List<UserMarkedEmoji> userMarkedEmojis = new ArrayList<>();;

    @OrderBy("scrapDateTime DESC")
    @JsonIgnoreProperties({"user"})
    @OneToMany(mappedBy = "user")
    private List<Scraps> scraps = new ArrayList<>();;

    @OneToMany(mappedBy = "user")
    private List<UserStickerSelected> userStickers = new ArrayList<>();

//    private Role role;

    @Builder
    public User(String userName, String email, String profilePicture) {
        this.userName = userName;
        this.email = email;
        this.profilePicture = profilePicture;
    }

    public void addSurvey(Survey survey) {
        this.survey = survey;
    }

}