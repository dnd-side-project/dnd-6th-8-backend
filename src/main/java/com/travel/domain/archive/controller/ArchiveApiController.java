package com.travel.domain.archive.controller;

import com.travel.domain.archive.dto.ArchivesResponseDto;
import com.travel.domain.archive.dto.ArchivesSaveRequestDto;
import com.travel.domain.archive.service.ArchivesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(tags = {"게시글 API 정보를 제공"})
public class ArchiveApiController {

    private final ArchivesService archivesService;

    @ApiOperation(value = "게시글을 저장하는 api")
    @PostMapping("/archives")
    public Long saveArchive(@RequestBody ArchivesSaveRequestDto archivesSaveRequestDto){
        return archivesService.save(archivesSaveRequestDto);
    }

    @ApiOperation(value = "게시글 하나가져오는 API")
    @GetMapping("/archives/{id}")
    public ArchivesResponseDto findById(@PathVariable Long id){
        return archivesService.findById(id);
    }

    @ApiOperation(value = "장소별로 게시물 필터링 API")
    @GetMapping("/archives/places")
    public String getArchiveListByPlace(){
        return " ";
    }

    @ApiOperation(value = "개인설문별로 게시물 필터링 API")
    @GetMapping("/archives/suggestion/survey")
    public String getArchiveBySurvey(){
        return "";
    }

    @ApiOperation(value = "기본 게시물 추천 API")
    @GetMapping("/archives/suggestion/")
    public String getArchiveBySuggestion(){return "";}

    @ApiOperation(value = "게시물 업데이트 API")
    @PostMapping("/archives/update/{archiveId}")
    public String updateArchive(){return "";}

    @ApiOperation(value = "게시글 삭제 API")
    @DeleteMapping("/archives/{archiveId}")
    public Long deleteArchive(@PathVariable Long archiveId){
        archivesService.delete(archiveId);
        return archiveId;
    }

    @ApiOperation(value = "게시물 공유여부변경")
    @PostMapping("/archives/share/{id}")
    public String changeArchiveStatus(){return "";}
}
