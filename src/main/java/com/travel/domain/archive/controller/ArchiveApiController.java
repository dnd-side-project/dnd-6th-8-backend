package com.travel.domain.archive.controller;

import com.travel.domain.archive.dto.ArchiveDetailResponseDto;
import com.travel.domain.archive.dto.ArchiveResponseDto;
import com.travel.domain.archive.dto.ArchivesSaveRequestDto;
import com.travel.domain.archive.dto.HomeResponse;
import com.travel.domain.archive.entity.EBadges;
import com.travel.domain.archive.entity.EPlaces;
import com.travel.domain.archive.entity.EReportType;
import com.travel.domain.archive.service.ArchivesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(tags = {"게시글 API 정보를 제공"})
public class ArchiveApiController {

    private final ArchivesService archivesService;

    @ApiOperation(value = "아카이브 생성 api")
    @PostMapping(path = "/archives")
    public ResponseEntity<ArchiveDetailResponseDto> saveArchive
            (@RequestPart(required = false) MultipartFile coverImage,
             @RequestPart ArchivesSaveRequestDto archivesSaveRequestDto,
             @ApiIgnore Principal principal){
        System.out.println(principal.getName());
        System.out.println("controller");
        System.out.println(coverImage);
        ArchiveDetailResponseDto archivesResponseDto = archivesService.saveArchive(coverImage, archivesSaveRequestDto, principal.getName());
        return ResponseEntity.created(URI.create("/api/v1/archives" + archivesResponseDto.getId()))
                .body(archivesResponseDto);
    }

    @ApiOperation(value = "아카이브 id로 가져오기 API")
    @GetMapping("/archives/{id}")
    public ResponseEntity<ArchiveDetailResponseDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(archivesService.findById(id));
    }

    @ApiOperation(value = "아카이브 업데이트 API")
    @PutMapping("/archives/{id}")
    public ResponseEntity<Void> updateArchive
            (@PathVariable Long id, @ModelAttribute ArchivesSaveRequestDto archivesSaveRequestDto){
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
    public ResponseEntity<List<ArchiveResponseDto>> getArchiveListByPlace(@RequestParam String place){
        List<ArchiveResponseDto> archivesResponseDtos = archivesService.findByPlace(place);
        return ResponseEntity.ok(archivesResponseDtos);
    }

    @ApiOperation(value = "추천 게시물 API")
    @GetMapping("/archives/suggestions")
    public ResponseEntity<List<ArchiveResponseDto>> getArchiveRecommendation(@ApiIgnore Principal principal){
        List<ArchiveResponseDto> archivesResponseDtos = archivesService.findByRecommendation(principal.getName());
        return ResponseEntity.ok(archivesResponseDtos);
    }

    @ApiOperation(value = "홈 ")
    @GetMapping("/archives/home")
    public ResponseEntity<HomeResponse> getMain(@ApiIgnore Principal principal){
        HomeResponse homeResponse = archivesService.getMain(principal.getName());
        return ResponseEntity.ok(homeResponse);
    }

    @ApiOperation(value = "아카이브 삭제 API")
    @DeleteMapping("/archives/{archiveId}")
    public ResponseEntity<Void> deleteArchive(@PathVariable Long archiveId){
        archivesService.delete(archiveId);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "뱃지 정하기")
    @PatchMapping("/archives/{archiveId}/badges")
    public ResponseEntity<Void> setBadge(@PathVariable Long archiveId, @RequestParam EBadges badges){
        archivesService.setBadges(archiveId, badges);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "아카이브 신고 API")
    @PostMapping("/report/archives/{archiveId}")
    public ResponseEntity<Void> reportArchive(@PathVariable Long archiveId, @ApiIgnore Principal principal,
                                              EReportType reportType){
        archivesService.reportArchive(archiveId, principal.getName(), reportType);
        return ResponseEntity.ok().build();
    }
}
