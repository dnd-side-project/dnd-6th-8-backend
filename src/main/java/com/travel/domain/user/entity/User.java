package com.travel.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.travel.domain.archive.entity.Archives;
import com.travel.domain.scrap.entity.Scraps;
import com.travel.domain.sticker.entity.UserStickerSelected;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Setter
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

    @Enumerated(EnumType.STRING)
    private EDiaryColor diaryColor;

    @Column()
    @OneToMany(mappedBy = "user")
    private List<Archives> archives = new ArrayList<>();

//    @OneToMany(mappedBy = "user")
//    private List<UserMarkedEmoji> userEmojiSelected = new ArrayList<>();;

    @OrderBy("createdAt DESC")
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

    public int countScrapsOfUser() { return this.scraps.size();}
}