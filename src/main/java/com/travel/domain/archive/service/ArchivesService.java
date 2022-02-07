package com.travel.domain.archive.service;

import com.travel.domain.archive.dto.ArchiveShareRequestDto;
import com.travel.domain.archive.dto.ArchivesResponseDto;
import com.travel.domain.archive.dto.ArchivesSaveRequestDto;


public interface ArchivesService {

    public ArchivesResponseDto saveArchive(ArchivesSaveRequestDto archivesSaveRequestDto);

    public ArchivesResponseDto findById(Long id);

    public void delete(Long id);

    public void updateArchive(Long id, ArchivesSaveRequestDto archivesSaveRequestDto);

    public void updateArchiveShare(Long id, boolean isShare);
}
