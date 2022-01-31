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

    @GetMapping("/archives/{id}")
    public ArchivesResponseDto findById(@PathVariable Long id){
        return archivesService.findById(id);
    }

    @GetMapping("/archives/places")
    public String getArchiveListByPlace(){
        return " ";
    }

    @GetMapping("/archives/suggestion/survey")
    public String getArchiveBySurvey(){
        return "";
    }

    @GetMapping("/archives/suggestion/")
    public String getArchiveBySuggestion(){return "";}

    @PostMapping("/archives/update/{archiveId}")
    public String updateArchive(){return "";}

    @DeleteMapping("/archives/{archiveId}")
    public Long deleteArchive(@PathVariable Long archiveId){
        archivesService.delete(archiveId);
        return archiveId;
    }

    @PostMapping("/archives/share/{id}")
    public String changeArchiveStatus(){return "";}
}
