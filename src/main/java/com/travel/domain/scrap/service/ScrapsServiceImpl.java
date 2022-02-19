package com.travel.domain.scrap.service;

import com.travel.domain.scrap.dto.ScrapPreviewDto;
import com.travel.domain.scrap.entity.Scraps;
import com.travel.domain.scrap.repository.ScrapsRepository;
import com.travel.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ScrapsServiceImpl implements ScrapsService {

    private final ScrapsRepository scrapsRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void addScraps(long archiveId) { //String loginEmail
        //User user = userRepository.findUserByEmail(loginEmail);
        scrapsRepository.addScraps(archiveId); //user.getId()
    }

    @Override
    @Transactional
    public ScrapPreviewDto addScraps(ScrapPreviewDto scrapPreviewDto) {
        Scraps scrap = scrapsRepository.save(scrapPreviewDto.toEntity());
        return new ScrapPreviewDto(scrap);
    }

    @Override
    @Transactional
    public void unScraps(long archiveId) { //String loginEmail
        //User user = userRepository.findUserByEmail(loginEmail);
        scrapsRepository.unScraps(archiveId); //user.getId()
    }
}
