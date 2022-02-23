package com.travel.domain.user.service;

import com.travel.domain.archive.dto.ArchiveDetailResponseDto;
import com.travel.domain.archive.entity.Archives;
import com.travel.domain.config.auth.test.UserDto;
import com.travel.domain.user.dto.UserSaveRequestDto;
import com.travel.domain.user.entity.User;
import com.travel.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    @Transactional
    public String save(UserDto userDto) {
        return userRepository.save(userDto.toEntity()).getEmail();
    }

    @Override
    @Transactional
    public UserDto findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if(user != null){
            return new UserDto(user);
        }
        return null;
    }
}
