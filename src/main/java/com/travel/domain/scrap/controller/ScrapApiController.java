package com.travel.domain.scrap.controller;

import com.travel.domain.scrap.dto.ScrapPreviewDto;
import com.travel.domain.scrap.dto.ScrapsSaveRequestDto;
import com.travel.domain.scrap.entity.Scraps;
import com.travel.domain.scrap.service.ScrapsService;
import com.travel.domain.user.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(tags = {"스크랩 API 정보를 제공"})
public class ScrapApiController {

    private final ScrapsService scrapsService;

    @ApiOperation(value = "스크랩 추가 api")
    @PostMapping("/archives/scraps")
    public ResponseEntity<ScrapPreviewDto> addScraps(@RequestParam Long archiveId, @RequestBody ScrapsSaveRequestDto scrapsSaveRequestDto){
        ScrapPreviewDto scrapPreviewDto = scrapsService.addScraps(scrapsSaveRequestDto, archiveId);
        return ResponseEntity.created(URI.create("/api/v1/archives/scraps" + scrapPreviewDto.getId())).body(scrapPreviewDto);
    }

    @ApiOperation(value = "스크랩 취소 api")
    @DeleteMapping("/archives/{scrapId}/unScraps")
    public void unScraps(@PathVariable long scrapId) { //Authentication authentication
        scrapsService.unScraps(scrapId); //authentication.getName()

    }

    @ApiOperation(value = "유저별 스크랩 목록 api")
    @GetMapping("/archives/scraps")
    public ResponseEntity<List<ScrapPreviewDto>> getScrapListByUser(@RequestParam String user){
        List<ScrapPreviewDto> scrapPreviewDtos = scrapsService.findByUser(user);
        return ResponseEntity.ok(scrapPreviewDtos);
    }
}