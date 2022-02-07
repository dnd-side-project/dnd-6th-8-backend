package com.travel.domain.archive.service;

import com.travel.domain.archive.dto.ArchiveDetailResponseDto;
import com.travel.domain.archive.dto.ArchiveResponseDto;
import com.travel.domain.archive.dto.ArchivesSaveRequestDto;
import com.travel.domain.archive.entity.Archives;
import com.travel.domain.archive.entity.EPlaces;
import com.travel.domain.archive.repository.ArchivesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ArchiveServiceImpl implements ArchivesService{

    private final ArchivesRepository archivesRepository;

    @Override
    @Transactional(readOnly=true)
    public ArchiveDetailResponseDto saveArchive(ArchivesSaveRequestDto archivesSaveRequestDto) {
        Archives archive = archivesRepository.save(archivesSaveRequestDto.toEntity());
        return new ArchiveDetailResponseDto(archive);
    }

    @Override
    @Transactional(readOnly=true)
    public ArchiveDetailResponseDto findById(Long id) {
        Archives archive = archivesRepository.findById(id).orElseThrow
                (() -> new IllegalArgumentException("해당 게시물이 없습니다. id = " + id));
        return new ArchiveDetailResponseDto(archive);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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
    @Transactional(readOnly = true)
    public List<ArchiveResponseDto> findByPlace(EPlaces place) {
        List<Archives> filtered = archivesRepository.findTop5ByPlace(place);
        return ArchiveResponseDto.listOf(filtered);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        Archives archive = archivesRepository.findById(id).orElseThrow
                (() -> new IllegalArgumentException("해당 게시물이 없습니다. id = " + id));
        archivesRepository.delete(archive);
    }


}
