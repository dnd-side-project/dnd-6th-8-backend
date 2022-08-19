package com.travel.domain.day.service;

import com.travel.domain.archive.entity.Archives;
import com.travel.domain.archive.repository.ArchivesRepository;
import com.travel.domain.common.S3Uploader;
import com.travel.domain.day.dto.*;
import com.travel.domain.day.entity.DayImage;
import com.travel.domain.day.entity.DaysInfo;
import com.travel.domain.day.repository.DayImageRepository;
import com.travel.domain.day.repository.DaysInfoRepository;
import com.travel.domain.day.repository.DaysRepository;
import com.travel.domain.day.entity.Days;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DayServiceImpl implements DaysService {

    private final DaysRepository daysRepository;
    private final ArchivesRepository archivesRepository;
    private final S3Uploader s3Uploader;
    private final DayImageRepository dayImageRepository;
    private final DaysInfoRepository daysInfoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<DaysSubjectiveResponseDto> saveDay(List<DaysSaveRequestDto> daysSaveRequestDto, Long archiveId) {
        System.out.println("saving");
        System.out.println(daysSaveRequestDto.size());

        Archives archive = archivesRepository.getById(archiveId);

        for (int i = 0; i < daysSaveRequestDto.size(); i++) {
            System.out.println("day" + i);
//            List<MultipartFile> dayImages = daysSaveRequestDto.get(i).getImages();
            List<DayInfoSaveRequestDto> daysInfosDto = daysSaveRequestDto.get(i).getDayInfoSaveRequestDtos();
            Days day = daysSaveRequestDto.get(i).toEntity();
            System.out.println("setting archive");
            day.setArchives(archive);
            day = daysRepository.save(day);

//            for (int j = 0; j > dayImages.size(); j++) {
//                try {
//                    String imageUrl = s3Uploader.upload(dayImages.get(j), "days");
//                    dayImageRepository.save(DaysImage.builder().imageUrl(imageUrl).days(day).build());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }

            for (int j = 0; j > daysInfosDto.size(); j++) {
                DayInfoSaveRequestDto dayInfoSaveRequestDto = daysInfosDto.get(j);
                daysInfoRepository.save(DaysInfo.builder()
                        .departure(dayInfoSaveRequestDto.getDeparture())
                        .arrival(dayInfoSaveRequestDto.getArrival())
                        .travelTime(dayInfoSaveRequestDto.getTravelTime())
                        .transportation(dayInfoSaveRequestDto.getTransportation()).build());
            }
        }
        return DaysSubjectiveResponseDto.listOf(archive.getDays());
    }


    //   day피드 별로 따로 저장
    @Override
    @Transactional(readOnly = true)
    public List<DaysSubjectiveResponseDto> saveDaySeparate(Long archiveId, Long dayNumber,
                                                           DaysSaveRequestDto daysSaveRequestDto,
                                                           List<MultipartFile> dayImages) {
        Archives archive = archivesRepository.getById(archiveId);
        Days day = daysSaveRequestDto.toEntity();

        day.setArchives(archive);
        day = daysRepository.save(day);


        int j = 0;
        while (dayImages != null && j < dayImages.size() && !dayImages.get(j).isEmpty()) {
            System.out.println("dayImage");
            try {
                String imageUrl = s3Uploader.upload(dayImages.get(j), "days");
                dayImageRepository.save(DayImage.builder().imageUrl(imageUrl).days(day).build());
            } catch (IOException e) {
                e.printStackTrace();
            }
            j++;
        }
        j = 0;

        List<DayInfoSaveRequestDto> daysInfosDto = daysSaveRequestDto.getDayInfoSaveRequestDtos();
        while (daysInfosDto != null && j < daysInfosDto.size()) {
            System.out.println("dayInfo");
            DayInfoSaveRequestDto dayInfoSaveRequestDto = daysInfosDto.get(j);
            daysInfoRepository.save(DaysInfo.builder()
                    .departure(dayInfoSaveRequestDto.getDeparture())
                    .arrival(dayInfoSaveRequestDto.getArrival())
                    .travelTime(dayInfoSaveRequestDto.getTravelTime())
                    .transportation(dayInfoSaveRequestDto.getTransportation()).build());
            j++;
        }

        return DaysSubjectiveResponseDto.listOf(archive.getDays());
    }

    public String imageUploader(MultipartFile image) {
        try {
            return s3Uploader.upload(image, "day");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public DaysInArchiveResponseDto getDays(Long archiveId) {
        //필요한 값 필터링
        List<Days> filteredDays = daysRepository.findByArchiveId(archiveId);
        List<DaysInfo> filteredDaysInfos = daysInfoRepository.findByArchiveId(archiveId);
        Archives filteredArchive = archivesRepository.findById(archiveId).orElseThrow(() -> new IllegalArgumentException("해당 아카이브가 없습니다. id = " + archiveId));
        ;
        List<DayImage> filteredDaysImgs = dayImageRepository.findByArchiveId(archiveId);
        //필터링된 값으로 Dto 생성
        List<DaysObjAndSubResponseDto> daysObjAndSubResponseDto = DaysObjAndSubResponseDto.listOf(filteredDays, filteredDaysInfos, filteredDaysImgs);
        DaysInArchiveResponseDto daysInArchiveResponseDto = new DaysInArchiveResponseDto(filteredArchive, daysObjAndSubResponseDto);
        return daysInArchiveResponseDto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDay(Long id, DaysSaveRequestDto daysSaveRequestDto) {
        Days day = daysRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 피드가 없습니다. id = " + id));

//        if (daysSaveRequestDto.getDayNumber() != 0) {
//            day.setDayNumber(daysSaveRequestDto.getDayNumber());
//        }
//        if (daysSaveRequestDto.getDate() != null) {
//            day.setDate(daysSaveRequestDto.getDate());
//        }
//        if (daysSaveRequestDto.getWeather() != null) {
//            day.setWeather(daysSaveRequestDto.getWeather());
//        }
//        if (daysSaveRequestDto.getImage() != null) {
//            day.setImage(daysSaveRequestDto.getImage());
//        }
        if (daysSaveRequestDto.getTravelDescription() != null) {
            day.setTravelDescription(daysSaveRequestDto.getTravelDescription());
        }
        if (daysSaveRequestDto.getEmotionDescription() != null) {
            day.setEmotionDescription(daysSaveRequestDto.getEmotionDescription());
        }
        if (daysSaveRequestDto.getTipDescription() != null) {
            day.setTipDescription(daysSaveRequestDto.getTipDescription());
        }

        daysRepository.save(day);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Days day = daysRepository.findById(id).orElseThrow
                (() -> new IllegalArgumentException("해당 피드가 없습니다. id = " + id));
        daysRepository.delete(day);
    }
}