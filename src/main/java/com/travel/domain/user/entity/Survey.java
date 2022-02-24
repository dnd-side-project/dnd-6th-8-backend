package com.travel.domain.user.entity;

import com.travel.domain.archive.entity.EArchivingStyle;
import com.travel.domain.archive.entity.EBudget;
import com.travel.domain.archive.entity.EPlaces;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
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

    @OneToOne(mappedBy = "survey")
    private User user;


    @Builder
    public Survey(EArchivingStyle archivingStyle, EBudget budget, boolean haveCompanion, User user) {
        this.haveCompanion = haveCompanion;
        this.budget = budget;
        setAuthor(user);
        this.archivingStyle = archivingStyle;
    }

    private void setAuthor(User user) {
        if (Objects.isNull(this.user)) {
            this.user = user;
            this.user.addSurvey(this);
        }
    }
}
