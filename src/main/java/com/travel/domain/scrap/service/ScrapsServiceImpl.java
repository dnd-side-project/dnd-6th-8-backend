package com.travel.domain.scrap.service;

import com.travel.domain.archive.entity.Archives;
import com.travel.domain.archive.repository.ArchivesRepository;
import com.travel.domain.scrap.dto.ScrapPreviewDto;
//import com.travel.domain.scrap.dto.ScrapsSaveRequestDto;
import com.travel.domain.scrap.entity.Scraps;
import com.travel.domain.scrap.repository.ScrapsRepository;
import com.travel.domain.user.entity.User;
import com.travel.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ScrapsServiceImpl implements ScrapsService {

    private final ScrapsRepository scrapsRepository;
    private final ArchivesRepository archivesRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly=true)
    public Scraps addScraps(Long ARCHIVE_ID, String loginEmail) {
        Archives archives = archivesRepository.findById(ARCHIVE_ID).orElseThrow(
                () -> new IllegalArgumentException("해당 게시물이 없습니다. id = " + ARCHIVE_ID));
        User user = userRepository.findByEmail(loginEmail);

        Scraps scrap = scrapsRepository.save(Scraps.builder().archives(archives).user(user).build());
        return scrap;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void unScraps(Long SCRAP_ID) {
        Scraps scrap = scrapsRepository.findById(SCRAP_ID).orElseThrow(
                ()->new IllegalArgumentException("해당 스크랩 내역이 없습니다. id = " + SCRAP_ID)
        );
        scrapsRepository.delete(scrap);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ScrapPreviewDto> getMyScrapPreviewList(String loginEmail) {
        User userEntity = userRepository.findByEmail(loginEmail);
        List<Scraps> scrapsOfLoggedInUser = scrapsRepository.findByUserId(userEntity.getId());
        return ScrapPreviewDto.getScrapListOfUser(scrapsOfLoggedInUser);
    }
}
