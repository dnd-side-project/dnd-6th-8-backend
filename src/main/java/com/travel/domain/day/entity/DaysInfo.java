package com.travel.domain.day.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class DaysInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String departure;

    private String arrival;

    private String travelTime;

    @Enumerated(EnumType.STRING)
    private ETransportation transportation;

    @ManyToOne
    @JoinColumn(name="DAYS_ID")
    private Days days;

    @Builder
    public DaysInfo(String departure, String arrival,
                    String travelTime, ETransportation transportation){
        this.departure = departure;
        this.arrival = arrival;
        this.travelTime = travelTime;
        this.transportation = transportation;
        setDays(days);
    }

    public void setDays(Days days) {
        this.days = days;
    }

}
