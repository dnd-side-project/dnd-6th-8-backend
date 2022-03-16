package com.travel.domain.archive.entity;

import com.travel.domain.day.entity.Days;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.travel.domain.emoji.entity.UserMarkedEmoji;
import com.travel.domain.scrap.entity.Scraps;
import com.travel.domain.sticker.entity.UserSticker;
import com.travel.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Archives {

    @Id
    @Column(name = "archiveId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String title;

    @Column()
    private boolean isShare;

    @Column()
    private String coverImage;

    @Column()
    @Enumerated(EnumType.STRING)
    private EPlaces place;

    @Column()
    private LocalDate firstDay;

    @Column()
    private LocalDate lastDay;

    @Column()
    @Enumerated(EnumType.STRING)
    private EBudget budget;

    @Column()
    @Enumerated(EnumType.STRING)
    private EArchivingStyle archivingStyle;

    @Column()
    private boolean haveCompanion;

    @OneToMany(mappedBy = "archives")
    private List<Days> days = new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnoreProperties({"archives"})
    @OneToMany(mappedBy = "archives")
    private List<Scraps> scrapsList;

    @OneToMany(mappedBy = "archive")
    List<UserMarkedEmoji> markedemojis = new ArrayList<>();

    @OneToMany(mappedBy = "archives")
    private List<Scraps> scraps = new ArrayList<>();

    @OneToMany(mappedBy = "archive")
    private List<UserSticker> userStickers = new ArrayList<>();

    @Builder
    public Archives(String title, boolean isShare, String coverImage,
                    EPlaces place, LocalDate firstDay, LocalDate lastDay, EBudget budget,
                    EArchivingStyle archivingStyle, boolean haveCompanion) {
        this.title = title;
        this.isShare = isShare;
        this.coverImage = coverImage;
        this.place = place;
        this.firstDay = firstDay;
        this.lastDay = lastDay;
        this.haveCompanion = haveCompanion;
        this.budget = budget;
        this.archivingStyle = archivingStyle;
    }
}

