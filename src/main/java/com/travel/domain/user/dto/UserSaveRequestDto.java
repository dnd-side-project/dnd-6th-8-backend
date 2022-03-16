package com.travel.domain.user.dto;


import com.travel.domain.user.entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;

public class UserSaveRequestDto{

    @ApiModelProperty(value = "사용자 아이디", example = "jihye", required = true)
    String userName;

    @Builder
    public UserSaveRequestDto(String userName) {
        this.userName = userName;
    }

    public User toEntity() {
        return User.builder().userName(userName).build();
    }
}
