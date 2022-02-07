package com.travel.domain.archive.dto;

import com.travel.domain.archive.entity.Archives;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@ApiModel(value = "아카이브 공유여부")
public class ArchiveShareRequestDto {

    @ApiModelProperty(value = "아카이브 공유", example = "true", required = true)
    private boolean isShare;

    @Builder
    public ArchiveShareRequestDto(boolean isShare){
        this.isShare = isShare;
    }

    public Archives toEntity() {
        return Archives.builder().isShare(isShare).build();
    }
}
