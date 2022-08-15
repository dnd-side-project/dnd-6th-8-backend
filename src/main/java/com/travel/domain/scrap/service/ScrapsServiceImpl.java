package com.travel.domain.scrap.service;

import com.travel.domain.archive.entity.Archives;
import com.travel.domain.archive.repository.ArchivesRepository;
import com.travel.domain.scrap.dto.ScrapListDto;
import com.travel.domain.scrap.dto.ScrapPreviewDto;
//import com.travel.domain.scrap.dto.ScrapsSaveRequestDto;
import com.travel.domain.scrap.entity.Scraps;
import com.travel.domain.scrap.repository.ScrapsRepository;
import com.travel.domain.user.entity.User;
import com.travel.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

//        Scraps scrap;  // 중복 스크랩 부분 추후 작업
//        scrapsRepository.existsByUserAndArchive(user.getId(), archives.getId())
//            System.out.println("이미 존재하는 스크랩입니다.");
//            return null;
//        } else {
//            scrap = scrapsRepository.save(Scraps.builder().archives(archives).user(user).build());
//        }

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
    public ScrapListDto getMyScrapPreviewList(String loginEmail) {
        User userEntity = userRepository.findByEmail(loginEmail);
        List<Scraps> scrapsOfLoggedInUser = scrapsRepository.findByUserId(userEntity.getId());

        List<ScrapPreviewDto> scrapPreviewDto = ScrapPreviewDto.getScrapListOfUser(scrapsOfLoggedInUser);
        int countMyScraps = userEntity.countScrapsOfUser();
        return new ScrapListDto(scrapPreviewDto, countMyScraps);
    }
}
