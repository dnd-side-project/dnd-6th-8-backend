package com.travel.domain.day.controller;

import com.travel.domain.archive.dto.ArchiveDetailResponseDto;
import com.travel.domain.archive.dto.ArchivesSaveRequestDto;
import com.travel.domain.day.dto.DayTotalRequestDto;
import com.travel.domain.day.dto.DaysObjAndSubResponseDto;
import com.travel.domain.day.dto.DaysSaveRequestDto;
import com.travel.domain.day.dto.DaysSubjectiveResponseDto;
import com.travel.domain.day.service.DaysService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(tags = {"데이 피드 API 정보를 제공"})
public class DayApiController {

    private final DaysService daysService;

     //day전체 데이터를 받기
     @ApiOperation(value = "데이 피드를 생성하는 API")
     @PostMapping("/archives/days/{archiveId}")
     public ResponseEntity<List<DaysSubjectiveResponseDto>> saveDay
             (@PathVariable Long archiveId, @ModelAttribute DayTotalRequestDto dayTotalRequestDto){
         System.out.println("saving");
         List<DaysSubjectiveResponseDto> dayDetailResponseDto = daysService.saveDay(dayTotalRequestDto.getDaysSaveRequestDto(), archiveId);
         return ResponseEntity.ok(dayDetailResponseDto);
     }

//    @ApiOperation(value = "아카이브 생성 api")
//    @PostMapping(path = "/archives")
//    public ResponseEntity<ArchiveDetailResponseDto> saveArchive
//            (@RequestPart(required = false) MultipartFile coverImage,
//             @RequestPart ArchivesSaveRequestDto archivesSaveRequestDto,
//             @ApiIgnore Principal principal){
//        System.out.println(principal.getName());
//        System.out.println("controller");
//        System.out.println(coverImage);
//        ArchiveDetailResponseDto archivesResponseDto = archivesService.saveArchive(coverImage, archivesSaveRequestDto, principal.getName());
//        return ResponseEntity.created(URI.create("/api/v1/archives" + archivesResponseDto.getId()))
//                .body(archivesResponseDto);
//    }

    //day별로 따로 데이터를 받기->
    @ApiOperation(value = "데이 피드를 생성하는 API")
    @PostMapping("/archives/{archiveId}/days/{dayNumber}")
    public ResponseEntity<List<DaysSubjectiveResponseDto>> saveDay
    (@PathVariable Long archiveId, @PathVariable Long dayNumber,
     @RequestPart(required = false) List<MultipartFile> dayImages,
     @RequestPart DaysSaveRequestDto daysSaveRequestDtos){
        List<DaysSubjectiveResponseDto> dayDetailResponseDto = daysService.saveDaySeparate(archiveId,
                dayNumber, daysSaveRequestDtos, dayImages);
        return ResponseEntity.ok(dayDetailResponseDto);
    }

    @ApiOperation(value = "데이 피드를 archiveId와 dayNumber로 가져오기 API")
    @GetMapping("/archives/{archiveId}/days/{dayNumber}")
    public ResponseEntity<DaysObjAndSubResponseDto> getDayListByArchivesAndDayNumber(@PathVariable Integer dayNumber, @PathVariable Long archiveId) {
        DaysObjAndSubResponseDto daysObjAndSubResponseDtoList = daysService.getDays(archiveId, dayNumber);
        return ResponseEntity.ok(daysObjAndSubResponseDtoList);
    }

    @ApiOperation(value = "데이 피드 업데이트 API")
    @PutMapping("/archives/{archiveId}/days/update/{dayNumber}")
    public ResponseEntity<Void> updateDay
            (@PathVariable Long dayNumber, @RequestBody DaysSaveRequestDto daysSaveRequestDto){
        daysService.updateDay(dayNumber, daysSaveRequestDto);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "데이 피드를 삭제하는 API")
    @DeleteMapping("/archives/{archiveId}/days/{dayNumber}")
    public ResponseEntity<Void> deleteDay(@PathVariable Long dayNumber){
        daysService.delete(dayNumber);
        return ResponseEntity.noContent().build();
    }
}