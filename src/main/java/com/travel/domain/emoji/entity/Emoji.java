package com.travel.domain.emoji.entity;

import com.travel.domain.archive.entity.EArchivingStyle;
import com.travel.domain.archive.entity.EBudget;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Emoji {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    String name;
}
