package com.travel.domain.archive.service;

import com.travel.domain.archive.dto.ArchivesResponseDto;
import com.travel.domain.archive.dto.ArchivesSaveRequestDto;
import com.travel.domain.archive.entity.Archives;
import com.travel.domain.archive.repository.ArchivesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ArchiveServiceImpl implements ArchivesService{

    private final ArchivesRepository archivesRepository;

    @Override
    @Transactional
    public ArchivesResponseDto save(ArchivesSaveRequestDto archivesSaveRequestDto) {
        Archives archive = archivesRepository.save(archivesSaveRequestDto.toEntity());
        return new ArchivesResponseDto(archive);
    }

    @Override
    @Transactional
    public ArchivesResponseDto findById(Long id) {
        Archives archive = archivesRepository.findById(id).orElseThrow
                (() -> new IllegalArgumentException("해당 게시물이 없습니다. id = " + id));
        return new ArchivesResponseDto(archive);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Archives archive = archivesRepository.findById(id).orElseThrow
                (() -> new IllegalArgumentException("해당 게시물이 없습니다. id = " + id));
        archivesRepository.delete(archive);
    }
}
