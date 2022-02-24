package com.travel.domain.scrap.service;

import com.travel.domain.scrap.dto.ScrapPreviewDto;
import com.travel.domain.scrap.dto.ScrapsSaveRequestDto;
import com.travel.domain.user.entity.User;

import java.util.List;

public interface ScrapsService {

    public ScrapPreviewDto addScraps(ScrapsSaveRequestDto scrapsSaveRequestDto, Long archiveId, String email);

    public void unScraps(long archiveId);

    public List<ScrapPreviewDto> findByEmail(String email);
}
