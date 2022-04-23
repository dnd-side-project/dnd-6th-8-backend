package com.travel.domain.user.service;

import com.travel.domain.archive.dto.ArchiveResponseDto;
import com.travel.domain.archive.entity.Archives;
import com.travel.domain.archive.entity.EBadges;
import com.travel.domain.archive.repository.ArchivesRepository;
import com.travel.domain.config.auth.UserDto;
import com.travel.domain.user.dto.MyPageResponse;
import com.travel.domain.user.dto.MyProfileResponse;
import com.travel.domain.user.dto.SurveyResponse;
import com.travel.domain.user.dto.UserDiaryColorRequest;
import com.travel.domain.user.entity.EDiaryColor;
import com.travel.domain.user.entity.Survey;
import com.travel.domain.user.entity.User;
import com.travel.domain.user.repository.SurveyRepository;
import com.travel.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final ArchivesRepository archivesRepository;
    private final SurveyRepository surveyRepository;

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

    @Override
    public MyPageResponse getUserInfo(String userEmail){
        User user = userRepository.findByEmail(userEmail);
        long archiveNum = archivesRepository.countByUser_Id(user.getId());
        List<String> badgesList = archivesRepository.findByBadgesAndUser_Id(user.getId());

        return MyPageResponse.builder().userName(user.getUserName())
                .archiveNumber(archiveNum).badgesList(badgesList).diaryColor(user.getDiaryColor()).build();
    }

    @Override
    public MyProfileResponse getUserProfile(String userEmail){
        User user = userRepository.findByEmail(userEmail);
        return MyProfileResponse.builder().userName(user.getUserName())
                .survey(user.getSurvey()).userEmail(user.getEmail()).build();
    }

    @Override
    public void setUserDiaryColor(String userEmail, UserDiaryColorRequest diaryColor){
        User user = userRepository.findByEmail(userEmail);
        user.setDiaryColor(diaryColor.getDiaryColor());
        userRepository.save(user);
    }
}