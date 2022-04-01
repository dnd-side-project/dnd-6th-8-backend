package com.travel.domain.scrap.entity;

import com.travel.domain.archive.entity.Archives;
import com.travel.domain.archive.entity.Place;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "archiveId")
    private Archives archives;

    @ManyToOne()
    @JoinColumn(name="userId")
    private User user;

    @Builder
    public Scraps(Archives archives, User user) {
        setArchives(archives);
        setUser(user);
    }
    public void setUser(User user) {
        if(this.user == null){
            this.user = user;
        }
    }

    public void setArchives(Archives archives) {
        this.archives = archives;
    }
}
