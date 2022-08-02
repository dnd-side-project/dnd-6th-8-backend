package com.travel.domain.day.entity;

import com.travel.domain.archive.entity.Archives;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class DayImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name="DAYS_ID")
    private Days days;

    @ManyToOne
    @JoinColumn(name="ARCHIVE_ID")
    private Archives archives;

    @Builder
    public DayImage(String imageUrl, Days days){
        this.imageUrl = imageUrl;
        setDays(days);
        this.archives = days.getArchives();
    }

    public void setDays(Days days) {
        this.days = days;
    }
}
