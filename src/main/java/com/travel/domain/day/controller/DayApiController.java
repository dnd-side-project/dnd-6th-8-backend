package com.travel.domain.day.controller;

import com.travel.domain.day.dto.DaysSaveRequestDto;
import com.travel.domain.day.dto.DaysResponseDto;
import com.travel.domain.day.service.DaysService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(tags = {"데이 피드 API 정보를 제공"})
public class DayApiController {

    private final DaysService daysService;

    @ApiOperation(value = "데이 피드를 저장하는 api")
    @PostMapping("/archives/{id}/days")
    public Long saveDay(@RequestBody DaysSaveRequestDto daysSaveRequestDto){
        return daysService.save(daysSaveRequestDto);
    }

    @GetMapping("/archives/{id}/days/{id}")
    public DaysResponseDto findById(@PathVariable Long id) {return daysService.findById(id); }

    @PostMapping("/archives/{id}/days/update/{id}")
    public String updateDay(){return "";}

    @DeleteMapping("/archives/{id}/days/{id}")
    public String deleteDay(){return "";}
}
