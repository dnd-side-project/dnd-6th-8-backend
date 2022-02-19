package com.travel.domain.scrap.controller;

import com.travel.domain.scrap.entity.Scraps;
import com.travel.domain.scrap.service.ScrapsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(tags = {"스크랩 API 정보를 제공"})
public class ScrapApiController {

    private final ScrapsService scrapsService;

    @ApiOperation(value = "스크랩 추가 api")
    @PostMapping("/archives/{archiveId}/scraps")
    public void addScraps(@PathVariable long archiveId) { //Authentication authentication
        scrapsService.addScraps(archiveId); //authentication.getName()
    }

    @ApiOperation(value = "스크랩 취소 api")
    @DeleteMapping("/archives/{archiveId}/unScraps")
    public void unScraps(@PathVariable long archiveId) { //Authentication authentication
        scrapsService.unScraps(archiveId); //authentication.getName()

    }
}