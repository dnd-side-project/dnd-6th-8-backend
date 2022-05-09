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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer day;

//    @Column()
//    private String weather;

    @Column()
    private String travelDescription;

    @Column()
    private String emotionDescription;

    @Column()
    private String tipDescription;

    @ManyToOne
    @JoinColumn(name= "ARCHIVE_ID")
    private Archives archives;

    @Builder
    public Days(Integer dayNumber, String travelDescription, String emotionDescription, String tipDescription) {
        this.day = dayNumber;
//        this.date = date;
//        this.weather = weather;
//        this.image = image;
        this.travelDescription = travelDescription;
        this.emotionDescription = emotionDescription;
        this.tipDescription = tipDescription;
        setArchives(archives);
    }

    public void setArchives(Archives archives) {
        System.out.println("setArchives");
        this.archives = archives;
    }
}
