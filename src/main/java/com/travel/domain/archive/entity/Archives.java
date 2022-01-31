package com.travel.domain.archive.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class Archives {

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
    private EArchivingStyle archivingStyle;

    @Column()
    private boolean isAlone;

    @Builder
    public Archives(String title, boolean isShare, String coverImage,
                    EPlaces place, LocalDate firstDay, LocalDate lastDay, EBudget budget,
                    EArchivingStyle archivingStyle, boolean isAlone) {
        this.title = title;
        this.isShare = isShare;
        this.coverImage = coverImage;
        this.place = place;
        this.firstDay = firstDay;
        this.lastDay = lastDay;
        this.isAlone = isAlone;
        this.budget = budget;
        this.archivingStyle = archivingStyle;
    }


}
