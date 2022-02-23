package com.travel.domain.scrap.service;

import com.travel.domain.archive.entity.Archives;
import com.travel.domain.archive.repository.ArchivesRepository;
import com.travel.domain.scrap.dto.ScrapPreviewDto;
import com.travel.domain.scrap.dto.ScrapsSaveRequestDto;
import com.travel.domain.scrap.entity.Scraps;
import com.travel.domain.scrap.repository.ScrapsRepository;
import com.travel.domain.user.entity.User;
import com.travel.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ScrapsServiceImpl implements ScrapsService {

    private final ScrapsRepository scrapsRepository;
    private final ArchivesRepository archivesRepository;

    @Override
    @Transactional(readOnly=true)
    public ScrapPreviewDto addScraps(ScrapsSaveRequestDto scrapsSaveRequestDto, Long archiveId) {
        Scraps scrap = scrapsSaveRequestDto.toEntity();
        Archives archives = archivesRepository.findById(archiveId).orElseThrow(
                () -> new IllegalArgumentException("해당 게시물이 없습니다. id = " + archiveId));
        scrap.setArchives(archives);
        scrapsRepository.save(scrap);
        return new ScrapPreviewDto(scrap);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void unScraps(long id) {
        Scraps scrap = scrapsRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("해당 스크랩 내역이 없습니다. id = " + id)
        );
        scrapsRepository.delete(scrap);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ScrapPreviewDto> findByUser(User user) {
        List<Scraps> filtered = scrapsRepository.findByUser(user);
        return ScrapPreviewDto.listOf(filtered);
    }
}
