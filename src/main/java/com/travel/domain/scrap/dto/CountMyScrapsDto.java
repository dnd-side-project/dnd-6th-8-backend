package com.travel.domain.scrap.dto;

import com.travel.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class CountMyScrapsDto {
    public int countMyScraps;

    public static CountMyScrapsDto from(User user) {
        if(user == null) {
            return null;
        }
        return CountMyScrapsDto.builder()
                .countMyScraps(user.countScrapsOfUser())
                .build();
    }

//    public static List<CountMyScrapsDto> listFrom(List<User> userList) {
//        if(userList == null){
//            return null;
//        }
//        return userList.stream()
//                .map(CountMyScrapsDto::from)
//                .collect(Collectors.toList());
//    }
}
