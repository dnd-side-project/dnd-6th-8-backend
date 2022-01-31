package com.travel.domain.archive.service;

import com.travel.domain.archive.dto.ArchivesResponseDto;
import com.travel.domain.archive.dto.ArchivesSaveRequestDto;
import com.travel.domain.archive.entity.Archives;
import com.travel.domain.archive.repository.ArchivesRepository;
import jdk.tools.jlink.internal.Archive;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


public interface ArchivesService {

    public Long save(ArchivesSaveRequestDto archivesSaveRequestDto);

    public ArchivesResponseDto findById(Long id);

    public void delete(Long id);
}
