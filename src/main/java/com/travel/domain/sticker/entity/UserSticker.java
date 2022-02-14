package com.travel.domain.sticker.entity;

import com.travel.domain.archive.entity.Archives;
import com.travel.domain.user.entity.User;

import javax.persistence.*;

public class UserSticker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sticker_id")
    private Sticker sticker;

    @ManyToOne
    @JoinColumn(name= "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name= "archive_id")
    private Archives archive;
}
