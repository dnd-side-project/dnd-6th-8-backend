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
public class DaysImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name="DAYS_ID")
    private Days days;

    @Builder
    public DaysImage(String imageUrl, Days days){
        this.imageUrl = imageUrl;
        setDays(days);
    }

    public void setDays(Days days) {
        this.days = days;
    }
}
