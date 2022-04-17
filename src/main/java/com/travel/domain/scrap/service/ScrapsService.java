package com.travel.domain.scrap.service;

import com.travel.domain.scrap.dto.ScrapPreviewDto;
import com.travel.domain.scrap.dto.ScrapsSaveRequestDto;
import com.travel.domain.scrap.entity.Scraps;
import com.travel.domain.user.entity.User;

import java.util.List;

public interface ScrapsService {

    public Scraps addScraps(String userEmail, Long archiveId);

    public void unScraps(long scrapId);

    public List<ScrapPreviewDto> findByUser(String user);
}
