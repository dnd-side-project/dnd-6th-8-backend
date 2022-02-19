package com.travel.domain.scrap.service;

import com.travel.domain.scrap.dto.ScrapPreviewDto;

public interface ScrapsService {

    public void addScraps(long archiveId); //String loginEmail

    public void unScraps(long archiveId); //String loginEmail
}
