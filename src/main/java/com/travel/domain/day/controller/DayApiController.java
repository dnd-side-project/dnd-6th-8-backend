package com.travel.domain.day.controller;

import com.travel.domain.day.dto.DaysSaveRequestDto;
import com.travel.domain.day.dto.DayDetailResponseDto;
import com.travel.domain.day.service.DaysService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(tags = {"데이 피드 API 정보를 제공"})
public class DayApiController {

    private final DaysService daysService;

    @ApiOperation(value = "데이 피드를 저장하는 API")
    @PostMapping("/archives/{id}/days")
    public ResponseEntity<DayDetailResponseDto> saveDay(@RequestBody DaysSaveRequestDto daysSaveRequestDto){
        DayDetailResponseDto dayDetailResponseDto = daysService.saveDay(daysSaveRequestDto);
        return ResponseEntity.created(URI.create("/api/v1/archives/{id}/days" + DayDetailResponseDto.getDayNumber())).body(dayDetailResponseDto);
    }

    @ApiOperation(value = "데이 피드를 id로 가져오기 API")
    @GetMapping("/archives/{id}/days/{id}")
    public ResponseEntity<DayDetailResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(daysService.findById(id));
    }

    @ApiOperation(value = "데이 피드를 수정하는 API")
    @PutMapping("/archives/{id}/days/update/{id}")
    public ResponseEntity<Void> updateDay
            (@PathVariable Long id, @RequestBody DaysSaveRequestDto daysSaveRequestDto){
        daysService.updateDay(id, daysSaveRequestDto);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "데이 피드를 삭제하는 API")
    @DeleteMapping("/archives/{id}/days/{id}")
    public ResponseEntity<Void> deleteDay(@PathVariable Long dayId){
        daysService.delete(dayId);
        return ResponseEntity.noContent().build();
    }
}
