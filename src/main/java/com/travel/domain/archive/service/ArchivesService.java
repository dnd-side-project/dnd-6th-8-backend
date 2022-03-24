package com.travel.domain.archive.service;

import com.travel.domain.archive.dto.ArchiveDetailResponseDto;
import com.travel.domain.archive.dto.ArchiveResponseDto;
import com.travel.domain.archive.dto.ArchivesSaveRequestDto;
import com.travel.domain.archive.entity.EBadges;
import com.travel.domain.archive.entity.EPlaces;

import java.util.List;


public interface ArchivesService {

    public ArchiveDetailResponseDto saveArchive(ArchivesSaveRequestDto archivesSaveRequestDto, String user);

    public ArchiveDetailResponseDto findById(Long id);

    public void delete(Long id);

    public void updateArchive(Long id, ArchivesSaveRequestDto archivesSaveRequestDto);

    public void updateArchiveShare(Long id, boolean isShare);

    public List<ArchiveResponseDto> findByPlace(String place);

    public List<ArchiveResponseDto> findByRecommendation(String userEmail);

    public void setBadges(Long id, EBadges badges);
}
