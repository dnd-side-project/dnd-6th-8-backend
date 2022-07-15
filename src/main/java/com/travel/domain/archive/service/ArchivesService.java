package com.travel.domain.archive.service;

import com.travel.domain.archive.dto.*;
import com.travel.domain.archive.entity.EBadges;
import com.travel.domain.archive.entity.EPlaces;
import com.travel.domain.archive.entity.EReportType;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface ArchivesService {

    public ArchiveDetailResponseDto saveArchive(MultipartFile coverImage, ArchivesSaveRequestDto archivesSaveRequestDto, String userEmail);
    public ArchiveDetailResponseDto findById(Long id);

    public void delete(Long id);

    public ArchiveDetailResponseDto updateArchive(MultipartFile coverImage, ArchiveUpdateRequestDto archiveUpdateRequestDto, String userEmail, Long archiveId);

    public void updateArchiveShare(Long id, boolean isShare);

    public List<ArchiveResponseDto> findByPlace(String place);

    public List<ArchiveResponseDto> findByRecommendation(String userEmail);

    public void setBadges(Long id, EBadges badges);

    public void reportArchive(long archiveId, String userEmail, EReportType reportType);

    public HomeResponse getMain(String userEmail);
}
