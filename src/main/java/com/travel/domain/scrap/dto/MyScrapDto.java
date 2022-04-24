package com.travel.domain.scrap.dto;

import com.travel.domain.archive.entity.Archives;
import com.travel.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class MyScrapDto {
    public int countScrapsOfUser;

    public static MyScrapDto from(User user) {
        if(user == null) {
            return null;
        }
        return MyScrapDto.builder()
                .countScrapsOfUser(user.countScrapsOfUser())
                .build();
    }

    public static List<MyScrapDto> listFrom(List<User> userList) {
        if(userList == null){
            return null;
        }
        return userList.stream()
                .map(MyScrapDto::from)
                .collect(Collectors.toList());
    }
}
