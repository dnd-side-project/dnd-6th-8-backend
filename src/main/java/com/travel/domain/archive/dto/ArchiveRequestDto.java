package com.travel.domain.archive.dto;

import com.travel.domain.archive.entity.EReportType;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "아카이브 신고하기")
public class ArchiveRequestDto {
    EReportType reportType;
}
