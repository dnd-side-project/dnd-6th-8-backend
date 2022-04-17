package com.travel.domain.archive.entity;

import com.travel.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private EReportType reportType;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name="ARCHIVES_ID")
    private Archives archives;

    @Builder
    public Report(EReportType reportType, User user, Archives archives) {
        this.reportType = reportType;
        setUser(user);
        setArchives(archives);
    }

    public void setUser(User user) {
        if(this.user == null){
            this.user = user;
        }
    }

    public void setArchives(Archives archives) {
        if(this.archives == null){
            this.archives = archives;
        }
    }
}
