package com.travel.domain.user.entity;

import com.travel.domain.archive.entity.EArchivingStyle;
import com.travel.domain.archive.entity.EBudget;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Survey {

    @Id
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
}
