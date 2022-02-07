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
    public ArchivesResponseDto saveArchive(ArchivesSaveRequestDto archivesSaveRequestDto) {
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
    public void updateArchive(Long id, ArchivesSaveRequestDto archivesSaveRequestDto){

        Archives archive = archivesRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시물이 없습니다. id = " + id));

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
            archive.setPlace(archivesSaveRequestDto.getPlace());
        }
        if (archivesSaveRequestDto.getBudget() != null) {
            archive.setBudget(archivesSaveRequestDto.getBudget());
        }
        if (archivesSaveRequestDto.isHaveCompanion() != archive.isHaveCompanion()) {
            archive.setHaveCompanion(archivesSaveRequestDto.isHaveCompanion());
        }

        archivesRepository.save(archive);
    }

    @Override
    public void updateArchiveShare(Long id, boolean isShare){

        Archives archive = archivesRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시물이 없습니다. id = " + id));

        if (archive.isShare() != isShare) {
            archive.setShare(isShare);
        }
        archivesRepository.save(archive);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Archives archive = archivesRepository.findById(id).orElseThrow
                (() -> new IllegalArgumentException("해당 게시물이 없습니다. id = " + id));
        archivesRepository.delete(archive);
    }


}
