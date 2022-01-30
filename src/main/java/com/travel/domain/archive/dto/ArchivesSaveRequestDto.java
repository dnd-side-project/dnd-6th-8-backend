package com.travel.domain.archive.dto;

import com.travel.domain.archive.entity.Archives;
import com.travel.domain.archive.entity.EPlaces;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

//import javax.validation.constraints.NotBlank;

@Getter
@ApiModel(value = "태그 생성 정보")
public class ArchivesSaveRequestDto {
    @ApiModelProperty(value = "아카이브 제목", example = "대충 다녀도 아름다운 제주", required = true)
    private String title;

    @ApiModelProperty(value = "여행 장소", example = "부산", required = true)
    private EPlaces place;




    @Builder
    public ArchivesSaveRequestDto(String title, EPlaces place){
        this.title = title;
        this.place = place;
    }

    public Archives toEntity(){
        return Archives.builder().title(title).place(place).build();
    }
}
