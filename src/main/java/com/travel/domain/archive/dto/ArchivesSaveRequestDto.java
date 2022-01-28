package com.travel.domain.archive.dto;

import com.travel.domain.archive.entity.Archives;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

//import javax.validation.constraints.NotBlank;

@Getter
@ApiModel(value = "태그 생성 정보")
public class ArchivesSaveRequestDto {
    @ApiModelProperty(value="태그의 이름", example = "이름입니다.", required = true)
//    @NotBlank(message = "태그 이름이 입력되지 않았습니다.")
    private String title;

    @Builder
    public ArchivesSaveRequestDto(String title){
        this.title = title;
    }

    public Archives toEntity(){
        return Archives.builder().title(title).build();
    }
}
