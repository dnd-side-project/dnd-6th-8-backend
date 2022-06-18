package com.travel.domain.user.dto;

import com.travel.domain.archive.entity.EBadges;
import com.travel.domain.user.entity.EDiaryColor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MyPageResponse {
    private String userName;
    private long archiveNumber;
    private EDiaryColor diaryColor;
    private List<String> badgesList;
}
