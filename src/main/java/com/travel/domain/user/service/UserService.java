package com.travel.domain.user.service;

import com.travel.domain.config.auth.UserDto;

public interface UserService {
    public String save(UserDto userDto);
    public UserDto findByEmail(String email);
}
