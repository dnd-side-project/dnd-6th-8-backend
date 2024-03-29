package com.travel.domain.scrap.service;

import com.travel.domain.scrap.dto.ScrapListDto;
import com.travel.domain.scrap.dto.ScrapPreviewDto;
//import com.travel.domain.scrap.dto.ScrapsSaveRequestDto;
import com.travel.domain.scrap.entity.Scraps;

import java.util.List;

public interface ScrapsService {

    public Scraps addScraps(Long archiveId, String loginEmail);

    public void unScraps(Long archiveId, String loginEmail);

    public ScrapListDto getMyScrapPreviewList(String loginEmail);
}
