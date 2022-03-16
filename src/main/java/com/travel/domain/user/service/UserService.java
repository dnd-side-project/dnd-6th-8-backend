package com.travel.domain.user.service;

import com.travel.domain.config.auth.test.UserDto;
import com.travel.domain.user.dto.UserSaveRequestDto;

import java.util.UUID;

public interface UserService {
    public String save(UserDto userDto);
    public UserDto findByEmail(String email);
}
