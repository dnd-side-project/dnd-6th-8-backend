package com.travel.domain.user.service;

import com.travel.domain.archive.dto.ArchiveResponseDto;
import com.travel.domain.config.auth.UserDto;
import com.travel.domain.user.dto.MyPageResponse;
import com.travel.domain.user.dto.MyProfileResponse;

import java.util.List;

public interface UserService {
    public String save(UserDto userDto);
    public UserDto findByEmail(String email);
    public List<ArchiveResponseDto> findArchiveByShare(boolean isShare, String userEmail);
    public MyPageResponse getUserInfo(String userEmail);
    public MyProfileResponse getUserProfile(String userEmail);
}
