package com.travel.domain.emoji.entity;

import com.travel.domain.archive.entity.Archives;
import com.travel.domain.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class UserMarkedEmoji {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    @Enumerated(EnumType.STRING)
    private EEmojis emoji;

    @ManyToOne
    @JoinColumn(name= "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name= "archiveId")
    private Archives archives;
}