package com.travel.domain.day.entity;

import javax.persistence.*;

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
}
