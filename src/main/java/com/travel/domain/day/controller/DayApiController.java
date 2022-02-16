package com.travel.domain.day.controller;

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
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(tags = {"데이 피드 API 정보를 제공"})
public class DayApiController {

    private final DaysService daysService;

    @ApiOperation(value = "데이 피드를 생성하는 API")
    @PostMapping("/archives/{archiveId}/days")
    public ResponseEntity<DayDetailResponseDto> saveDay
            (@PathVariable Long archiveId, @RequestBody DaysSaveRequestDto daysSaveRequestDto){
        DayDetailResponseDto dayDetailResponseDto = daysService.saveDay(daysSaveRequestDto);
        return ResponseEntity.created(URI.create("/api/v1/archives/days" + dayDetailResponseDto.getDayNumber())).body(dayDetailResponseDto);
    }

    @ApiOperation(value = "데이 피드를 archiveId와 dayNumber로 가져오기 API")
    @GetMapping("/archives/{archiveId}/days/{dayNumber}")
    public ResponseEntity<List<DayDetailResponseDto>> getDayListByArchiveIdAndDayNumber(@PathVariable Days archiveId, Days dayNumber) {
        List<DayDetailResponseDto> dayDetailResponseDtos = daysService.findByArchiveIdAndDayNumber(archiveId, dayNumber);
        return ResponseEntity.ok(dayDetailResponseDtos);
    }

//    @ApiOperation(value = "데이 피드를 archiveId와 dayNumber로 가져오기 API")
//    @GetMapping("/archives/{archiveId}/days/{dayNumber}")
//    public ResponseEntity<DayDetailResponseDto> findById(@PathVariable Long archiveId, Long dayNumber) {
//        return ResponseEntity.ok(daysService.findById(dayNumber));
//    }

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
