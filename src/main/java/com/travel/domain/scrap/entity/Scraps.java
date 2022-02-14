package com.travel.domain.scrap.entity;

import com.travel.domain.archive.entity.Archives;
import com.travel.domain.emoji.entity.Emoji;
import com.travel.domain.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Scraps {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name= "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name= "archive_id")
    private Archives archive;
}