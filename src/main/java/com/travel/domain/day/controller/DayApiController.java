package com.travel.domain.day.controller;

import com.travel.domain.archive.entity.Archives;
import com.travel.domain.day.dto.DayTotalRequestDto;
import com.travel.domain.day.dto.DaysSaveRequestDto;
import com.travel.domain.day.dto.DayDetailResponseDto;
import com.travel.domain.day.entity.Days;
import com.travel.domain.day.service.DaysService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(tags = {"데이 피드 API 정보를 제공"})
public class DayApiController {

    private final DaysService daysService;

     @ApiOperation(value = "데이 피드를 생성하는 API")
     @PostMapping("/archives/days")
     public ResponseEntity<List<DayDetailResponseDto>> saveDay
             (@RequestParam Long archiveId, @ModelAttribute DayTotalRequestDto dayTotalRequestDto){
         List<DayDetailResponseDto> dayDetailResponseDto = daysService.saveDay(dayTotalRequestDto.getDaysSaveRequestDto(), archiveId);
         return ResponseEntity.ok(dayDetailResponseDto);
     }

    @ApiOperation(value = "데이 피드를 archiveId와 dayNumber로 가져오기 API")  //PathVariable -> RequestParam
    @GetMapping("/archives/days/{dayNumber}")
    public ResponseEntity<List<DayDetailResponseDto>> getDayListByArchivesAndDayNumber(@PathVariable Integer dayNumber, @RequestParam Archives archives) {
        List<DayDetailResponseDto> dayDetailResponseDtos = daysService.getDays(archives, dayNumber);
        return ResponseEntity.ok(dayDetailResponseDtos);
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