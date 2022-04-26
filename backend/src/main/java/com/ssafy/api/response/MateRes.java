package com.ssafy.api.response;

import com.ssafy.domain.entity.Activity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@ApiModel("MateResponse")
public class MateRes {

    @ApiModelProperty(name = "Activity id")
    Long activityId;

    @ApiModelProperty(name = "생성 날짜")
    LocalDateTime createdDate;

    @ApiModelProperty(name = "카테고리 번호")
    int categoryId;

    @ApiModelProperty(name = "제목")
    String title;

    @ApiModelProperty(name = "설명")
    String description;

    @ApiModelProperty(name = "위치 주소")
    String location;

    @ApiModelProperty(name = "팀 여부")
    boolean isTeam;

    public static MateRes of(Activity mate) {

        return null;
    }
}
