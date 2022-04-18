package com.travel.domain.archive.dto;

import com.travel.domain.archive.entity.Archives;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class HomeResponse {
    private String firstArchiveImage;
    private String secondArchiveImage;
    private String thirdArchiveImage;
    private long totalArchiveNum;
    private String userNickName;

    public HomeResponse(List<Archives> archives, long totalArchiveNum, String userName) {
        System.out.println(archives.size());
        this.firstArchiveImage = archives.get(0).getCoverImage();
        this.secondArchiveImage = archives.get(1).getCoverImage();
        this.thirdArchiveImage = archives.get(2).getCoverImage();
        this.totalArchiveNum = totalArchiveNum;
        this.userNickName = userName;
    }
}
