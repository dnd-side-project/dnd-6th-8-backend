package com.travel.domain.archive.service;

import com.travel.domain.archive.dto.ArchivesResponseDto;
import com.travel.domain.archive.dto.ArchivesSaveRequestDto;
import com.travel.domain.archive.entity.Archives;
import com.travel.domain.archive.repository.ArchivesRepository;
import jdk.tools.jlink.internal.Archive;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ArchivesService {

    private final ArchivesRepository archivesRepository;

    @Transactional
    public Long save(ArchivesSaveRequestDto archivesSaveRequestDto){
        return archivesRepository.save(archivesSaveRequestDto.toEntity()).getId();
    }

    @Transactional
    public ArchivesResponseDto findById(Long id ){
        Archives entity = archivesRepository.findById(id).orElseThrow
                (()-> new IllegalArgumentException("해당 게시물이 없습니다. id = " + id));
        return new ArchivesResponseDto(entity);
    }
}
