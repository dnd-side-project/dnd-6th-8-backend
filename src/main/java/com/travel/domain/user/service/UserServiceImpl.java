package com.travel.domain.user.service;

import com.travel.domain.archive.dto.ArchiveResponseDto;
import com.travel.domain.archive.entity.Archives;
import com.travel.domain.archive.repository.ArchivesRepository;
import com.travel.domain.config.auth.UserDto;
import com.travel.domain.user.entity.User;
import com.travel.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final ArchivesRepository archivesRepository;

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

    public List<ArchiveResponseDto> findArchiveByShare(boolean isShare, String userEmail){
        User user = userRepository.findByEmail(userEmail);
        List<Archives> archives = archivesRepository.findByIsShareAndUser_Id(isShare, user.getId());
        return ArchiveResponseDto.listOf(archives);
    }
}