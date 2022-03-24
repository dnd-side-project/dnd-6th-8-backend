package com.travel.domain.archive.service;

import com.travel.domain.archive.dto.ArchiveDetailResponseDto;
import com.travel.domain.archive.dto.ArchiveResponseDto;
import com.travel.domain.archive.dto.ArchivesSaveRequestDto;
import com.travel.domain.archive.entity.Archives;
import com.travel.domain.archive.entity.EBadges;
import com.travel.domain.archive.entity.EPlaces;
import com.travel.domain.archive.entity.Place;
import com.travel.domain.archive.repository.ArchivesRepository;
import com.travel.domain.archive.repository.PlaceRepository;
import com.travel.domain.common.S3Uploader;
import com.travel.domain.user.entity.Survey;
import com.travel.domain.user.entity.User;
import com.travel.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ArchiveServiceImpl implements ArchivesService {

    private final ArchivesRepository archivesRepository;
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;
    private final S3Uploader s3Uploader;

    @Override
    @Transactional(readOnly = true)
    public ArchiveDetailResponseDto saveArchive(ArchivesSaveRequestDto archivesSaveRequestDto, String userEmail) {
        User user = userRepository.findByEmail(userEmail);
        boolean placeExists = placeRepository.existsByName(archivesSaveRequestDto.getPlace());

        Place place = placeHandler(archivesSaveRequestDto.getPlace());

        String imageUrl = null;
        System.out.println(archivesSaveRequestDto.getCoverPicture());
        if (archivesSaveRequestDto.getCoverPicture() != null) {
            System.out.println("image not null");
            try {
                imageUrl = s3Uploader.upload(archivesSaveRequestDto.getCoverPicture()
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
    public ArchiveDetailResponseDto findById(Long id) {
        Archives archive = archivesRepository.findById(id).orElseThrow
                (() -> new IllegalArgumentException("해당 게시물이 없습니다. id = " + id));
        return new ArchiveDetailResponseDto(archive);
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
    @Transactional(rollbackFor = Exception.class)
    public void updateArchive(Long id, ArchivesSaveRequestDto archivesSaveRequestDto) {

        Archives archive = archivesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id = " + id));

        if (archivesSaveRequestDto.getTitle() != null) {
            archive.setTitle(archivesSaveRequestDto.getTitle());
        }
        if (archivesSaveRequestDto.getFirstDay() != null) {
            archive.setFirstDay(archivesSaveRequestDto.getFirstDay());
        }
        if (archivesSaveRequestDto.getLastDay() != null) {
            archive.setLastDay(archivesSaveRequestDto.getLastDay());
        }
        if (archivesSaveRequestDto.getArchivingStyle() != null) {
            archive.setArchivingStyle(archivesSaveRequestDto.getArchivingStyle());
        }
        if (archivesSaveRequestDto.getPlace() != null) {
            archive.setPlace(placeHandler(archivesSaveRequestDto.getPlace()));
        }
        if (archivesSaveRequestDto.getBudget() != null) {
            archive.setBudget(archivesSaveRequestDto.getBudget());
        }
        if (archivesSaveRequestDto.isHaveCompanion() != archive.isHaveCompanion()) {
            archive.setHaveCompanion(archivesSaveRequestDto.isHaveCompanion());
        }

        if(archivesSaveRequestDto.getCoverPicture() != null){
            String imageUrl = null;
            try {
                s3Uploader.deleteS3(archive.getCoverImage(),"archive" );
                imageUrl = s3Uploader.upload(archivesSaveRequestDto.getCoverPicture()
                        , "archive");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        archivesRepository.save(archive);
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
        archivesRepository.delete(archive);
    }


}
