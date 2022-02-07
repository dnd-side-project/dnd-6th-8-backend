package com.travel.domain.archive.controller;

import com.travel.domain.archive.dto.ArchivesResponseDto;
import com.travel.domain.archive.dto.ArchivesSaveRequestDto;
import com.travel.domain.archive.service.ArchivesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(tags = {"게시글 API 정보를 제공"})
public class ArchiveApiController {

    private final ArchivesService archivesService;

    @ApiOperation(value = "아카이브 생성 api")
    @PostMapping("/archives")
    public ResponseEntity<ArchivesResponseDto> saveArchive
            (@RequestBody ArchivesSaveRequestDto archivesSaveRequestDto){
        ArchivesResponseDto archivesResponseDto = archivesService.saveArchive(archivesSaveRequestDto);
        return ResponseEntity.created(URI.create("/api/v1/archives" + archivesResponseDto.getId()))
                .body(archivesResponseDto);
    }

    @ApiOperation(value = "아카이브 id로 가져오기 API")
    @GetMapping("/archives/{id}")
    public ResponseEntity<ArchivesResponseDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(archivesService.findById(id));
    }

    @ApiOperation(value = "아카이브 업데이트 API")
    @PutMapping("/archives/{id}")
    public ResponseEntity<Void> updateArchive
            (@PathVariable Long id, @RequestBody ArchivesSaveRequestDto archivesSaveRequestDto){
        archivesService.updateArchive(id, archivesSaveRequestDto);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "아카이브 공유여부변경 API")
    @PutMapping("/archives/{id}/share")
    public ResponseEntity<Void> changeArchiveStatus
            (@PathVariable Long id, @RequestParam boolean isShare){
        archivesService.updateArchiveShare(id, isShare);
        return ResponseEntity.ok().build();
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



    @ApiOperation(value = "게시글 삭제 API")
    @DeleteMapping("/archives/{archiveId}")
    public Long deleteArchive(@PathVariable Long archiveId){
        archivesService.delete(archiveId);
        return archiveId;
    }

}
