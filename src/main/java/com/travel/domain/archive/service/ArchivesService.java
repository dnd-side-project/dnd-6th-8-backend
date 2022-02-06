package com.travel.domain.archive.service;

import com.travel.domain.archive.dto.ArchivesResponseDto;
import com.travel.domain.archive.dto.ArchivesSaveRequestDto;



public interface ArchivesService {

    public ArchivesResponseDto save(ArchivesSaveRequestDto archivesSaveRequestDto);

    public ArchivesResponseDto findById(Long id);

    public void delete(Long id);
}
