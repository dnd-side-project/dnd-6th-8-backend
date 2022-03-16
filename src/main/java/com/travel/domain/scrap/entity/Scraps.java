package com.travel.domain.scrap.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.travel.domain.archive.entity.Archives;
import com.travel.domain.emoji.entity.Emoji;
import com.travel.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Scraps {

    @Id
    @Column(name = "scrapId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    @CreatedDate
    private LocalDateTime scrapDateTime;

    @ManyToOne
    @JoinColumn(name= "archiveId")
    private Archives archives;

//    @ManyToOne
//    @JoinColumn(name= "userId")
//    @JsonIgnoreProperties({"archivesList"})
//    private User user;

    @Builder
    public Scraps(LocalDateTime scrapDateTime) {
//        this.archives = archives;
        this.scrapDateTime = scrapDateTime;
//        this.user = user;
    }
}
