package com.travel.domain.user.service;

import com.travel.domain.user.dto.UserSaveRequestDto;

import java.util.UUID;

public interface UserService {
    public int save(UserSaveRequestDto userSaveRequestDto);
}
