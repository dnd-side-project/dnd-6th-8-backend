package com.travel.domain.day.entity;

import com.travel.domain.archive.entity.Archives;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Days {

    @Id
    @Column(name = "dayId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dayNumber")
    private int dayNumber;

    @Column
    private LocalDate date;

    @Column()
    private String weather;

    @Column()
    private String image;

    @Column()
    private String travelDescription;

    @Column()
    private String emotionDescription;

    @Column()
    private String tipDescription;

    @ManyToOne
    @JoinColumn(name = "archiveId")
    private Archives archives;

    @Builder
    public Days(int dayNumber, LocalDate date, String weather, String image, String travelDescription, String emotionDescription, String tipDescription, Archives archives) {
        this.dayNumber = dayNumber;
        this.date = date;
        this.weather = weather;
        this.image = image;
        this.travelDescription = travelDescription;
        this.emotionDescription = emotionDescription;
        this.tipDescription = tipDescription;
        this.archives = archives;
    }

}
