package com.travel.domain.archive.service;

import com.travel.domain.archive.dto.ArchiveDetailResponseDto;
import com.travel.domain.archive.dto.ArchiveResponseDto;
import com.travel.domain.archive.dto.ArchivesSaveRequestDto;
import com.travel.domain.archive.dto.HomeResponse;
import com.travel.domain.archive.entity.*;
import com.travel.domain.archive.repository.ArchivesRepository;
import com.travel.domain.archive.repository.PlaceRepository;
import com.travel.domain.archive.repository.ReportRepository;
import com.travel.domain.common.S3Uploader;
import com.travel.domain.emoji.dto.EmojiResponse;
import com.travel.domain.emoji.repository.UserEmojiSelectedRepository;
import com.travel.domain.user.entity.Survey;
import com.travel.domain.user.entity.User;
import com.travel.domain.user.repository.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ArchiveServiceImpl implements ArchivesService {

    private final ArchivesRepository archivesRepository;
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;
    private final S3Uploader s3Uploader;
    private final ReportRepository reportRepository;
    private final UserEmojiSelectedRepository userEmojiSelectedRepository;

    @Override
    @Transactional(readOnly = true)
    public ArchiveDetailResponseDto saveArchive(MultipartFile coverImage, ArchivesSaveRequestDto archivesSaveRequestDto, String userEmail) {
        User user = userRepository.findByEmail(userEmail);
        boolean placeExists = placeRepository.existsByName(archivesSaveRequestDto.getPlaces());

        Place place = placeHandler(archivesSaveRequestDto.getPlaces());

        String imageUrl = null;

        System.out.println(coverImage == null);
        if (coverImage != null) {
            System.out.println("image not null");
            try {
                imageUrl = s3Uploader.upload(coverImage
                        , "archive");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Archives archive = archivesRepository.save(archivesSaveRequestDto.toEntity(user, place, imageUrl));
        return new ArchiveDetailResponseDto(archive);
    }

    @Override
    @Transactional(readOnly = true)
    public ArchiveDetailResponseDto updateArchive(MultipartFile coverImage, ArchivesSaveRequestDto archivesSaveRequestDto, String userEmail, Long archiveId) {
        User user = userRepository.findByEmail(userEmail);
        Archives archive = archivesRepository.getById(archiveId);

        if (archivesSaveRequestDto.getTitle() != null) {
            archive.setTitle(archivesSaveRequestDto.getTitle());
        }
        if (archivesSaveRequestDto.getFirstDay() != null) {
            archive.setFirstDay(LocalDate.parse(archivesSaveRequestDto.getFirstDay()));
        }
        if (archivesSaveRequestDto.getLastDay() != null) {
            archive.setLastDay(LocalDate.parse(archivesSaveRequestDto.getLastDay()));
        }
        if (archivesSaveRequestDto.getArchivingStyle() != null) {
            archive.setArchivingStyle(archivesSaveRequestDto.getArchivingStyle());
        }
        if (archivesSaveRequestDto.getPlaces() != null) {
            boolean placeExists = placeRepository.existsByName(archivesSaveRequestDto.getPlaces());
            Place place = placeHandler(archivesSaveRequestDto.getPlaces());
            archive.setPlace(place);
        }
        if (archivesSaveRequestDto.getBudget() != null) {
            archive.setBudget(archivesSaveRequestDto.getBudget());
        }
        if (Boolean.parseBoolean(archivesSaveRequestDto.getHaveCompanion()) != archive.isHaveCompanion()) {
            archive.setHaveCompanion(Boolean.parseBoolean(archivesSaveRequestDto.getHaveCompanion()));
        }

        String imageUrl = null;

        System.out.println(coverImage.isEmpty());
        if (coverImage.getResource().exists()) {
            System.out.println("image not null");
            try {
                imageUrl = s3Uploader.upload(coverImage
                        , "archive");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        archivesRepository.save(archive);
        return new ArchiveDetailResponseDto(archive);
    }

    @Override
    @Transactional(readOnly = true)
    public ArchiveDetailResponseDto findById(Long id) {
        Archives archive = archivesRepository.findById(id).orElseThrow
                (() -> new IllegalArgumentException("해당 게시물이 없습니다. id = " + id));
        return new ArchiveDetailResponseDto(archive);
    }

    @Override
    @Transactional(readOnly = true)
    public HomeResponse getMain(String userEmail) {
        Pageable limitThree = PageRequest.of(0,3);
        LocalDateTime sevenDaysAgo = LocalDateTime.now().minusDays(7);
        User user = userRepository.findByEmail(userEmail);
        List<Archives> archivesList = userEmojiSelectedRepository.orderByCount(sevenDaysAgo, limitThree);
        long totalArchiveNum = archivesRepository.count();
        return new HomeResponse(archivesList, totalArchiveNum, user.getUserName());
    }

    @Override
    public void setBadges(Long id, EBadges badges) {
        Archives archive = archivesRepository.findById(id).orElseThrow
                (() -> new IllegalArgumentException("해당 게시물이 없습니다. id = " + id));
        archive.setBadges(badges);
        archivesRepository.save(archive);
    }

    public Place placeHandler(String placeName) {
        boolean placeExists = placeRepository.existsByName(placeName);
        Place place = null;
        if (placeExists) {
            place = placeRepository.getByName(placeName);
        } else {
            place = placeRepository.save(place.builder()
                    .name(placeName).build());
        }
        return place;
    }


    @Override
    public void updateArchiveShare(Long id, boolean isShare) {

        Archives archive = archivesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id = " + id));

        if (archive.isShare() != isShare) {
            archive.setShare(isShare);
        }
        archivesRepository.save(archive);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ArchiveResponseDto> findByPlace(String place) {
        Place placeEntity = placeRepository.getByName(place);
        List<Archives> filtered = archivesRepository.findByPlace_Id(placeEntity.getId());
        return ArchiveResponseDto.listOf(filtered);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ArchiveResponseDto> findByRecommendation(String userEmail) {
        User user = userRepository.findByEmail(userEmail);
        List<Archives> archivesList;
        if(user.getSurvey() != null){
            archivesList = findBySurvey(user.getSurvey());
        }else{
            archivesList = defaultRecommendation();
        }
        return ArchiveResponseDto.listOf(archivesList);
    }

    private List<Archives> findBySurvey(Survey survey){
        System.out.println(survey.getBudget());
        return archivesRepository.findByArchivingStyleAndBudgetAndHaveCompanion(survey.getArchivingStyle().toString(),survey.getBudget().toString(),survey.isHaveCompanion());
    }

    private List<Archives> defaultRecommendation(){
        return archivesRepository.findRandom();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        Archives archive = archivesRepository.findById(id).orElseThrow
                (() -> new IllegalArgumentException("해당 게시물이 없습니다. id = " + id));
        if(archive.getCoverImage() != null){
            s3Uploader.deleteS3(archive.getCoverImage(), "archive");
        }
        archivesRepository.delete(archive);
    }

    @Override
    public void reportArchive(long archiveId, String userEmail, EReportType reportType){
        Archives archives = archivesRepository.getById(archiveId);
        User user = userRepository.findByEmail(userEmail);
        Report report = Report.builder().reportType(reportType).archives(archives).user(user).build();
        reportRepository.save(report);
    }


}
