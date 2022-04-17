package com.travel.domain.scrap.entity;

import com.travel.domain.archive.entity.Archives;
import com.travel.domain.common.BaseTimeEntity;
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
public class Scraps extends BaseTimeEntity {

    @Id
    @Column(name = "SCRAP_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name= "ARCHIVE_ID")
    private Archives archives;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Builder
    public Scraps(Archives archives, User user) {
        this.archives = archives;
        this.user = user;
    }
}
