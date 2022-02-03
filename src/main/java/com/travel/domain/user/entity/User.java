package com.travel.domain.user.entity;

import com.travel.domain.archive.entity.Archives;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private String id;

    @Column()
    private String userName;

//    @Column()
////    @OneToMany(mappedBy = )
//    private Archives archives;
}
