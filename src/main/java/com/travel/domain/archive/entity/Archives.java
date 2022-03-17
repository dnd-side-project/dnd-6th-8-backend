package com.travel.domain.archive.entity;

import com.travel.domain.common.BaseTimeEntity;
import com.travel.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Archives extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String title;

    @Column()
    private boolean isShare;

    @Column()
    private String coverImage;

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

    @ManyToOne()
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name="PLACE_ID")
    private Place place;
//
//    @Column()
//    @Enumerated(EnumType.STRING)
//    private EPlaces place;


    @Builder
    public Archives(String title, boolean isShare, String coverImage,
                    Place place, LocalDate firstDay, LocalDate lastDay, EBudget budget, EPlaces places,
                    EArchivingStyle archivingStyle, boolean haveCompanion, User user) {
        this.title = title;
        this.isShare = isShare;
        this.coverImage = coverImage;
        this.firstDay = firstDay;
        this.lastDay = lastDay;
        this.haveCompanion = haveCompanion;
        this.budget = budget;
        this.archivingStyle = archivingStyle;
        setUser(user);
        setPlace(place);
    }

    public void setUser(User user) {
        if(this.user != null){
            this.user = user;
        }
    }

    public void setPlace(Place place) {
        if(this.place != null){
            this.place = place;
        }
    }
}

