package com.travel.domain.scrap.service;

import com.travel.domain.scrap.dto.ScrapPreviewDto;
import com.travel.domain.scrap.dto.ScrapsSaveRequestDto;

public interface ScrapsService {

    public ScrapPreviewDto addScraps(ScrapsSaveRequestDto scrapsSaveRequestDto, Long archiveId);

    public void unScraps(long archiveId); //String loginEmail
}
