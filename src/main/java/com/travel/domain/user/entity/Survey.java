package com.travel.domain.user.entity;

import com.travel.domain.archive.entity.EArchivingStyle;
import com.travel.domain.archive.entity.EBudget;
import com.travel.domain.archive.entity.EPlaces;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class Survey {

    @Id
    @Column(name = "survey_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    @Enumerated(EnumType.STRING)
    EArchivingStyle archivingStyle;

    @Column()
    @Enumerated(EnumType.STRING)
    EBudget budget;

    @Column()
    private boolean haveCompanion;

    @Builder
    public Survey(EArchivingStyle archivingStyle, EBudget budget, boolean haveCompanion) {
        this.haveCompanion = haveCompanion;
        this.budget = budget;
        this.archivingStyle = archivingStyle;
    }
}
