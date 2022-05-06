package com.ssafy.api.controller;

import com.ssafy.api.request.PlaceSearchPostReq;
import com.ssafy.api.response.BaseResponseBody;
import com.ssafy.api.response.PlaceListRes;
import com.ssafy.api.service.ActivityService;
import com.ssafy.api.service.PlaceService;
import com.ssafy.domain.entity.Activity;
import com.ssafy.domain.entity.PlaceMongo;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ssafy.common.statuscode.MemberCode.SUCCESS_EMAIL_NOT_FOUND;

/**
 * 체육 시설 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "체육 시설 관련 API", tags = {"Places"})
@RestController
@RequestMapping("/api/v1/places")
public class PlaceController {

    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }
    
    @PostMapping("/search")
    @ApiOperation(value = "체육 시설 목록 정보", notes = "<strong>전체 체육 시설 목록</strong>을 넘겨준다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = PlaceListRes.class),
    })
    public ResponseEntity<? extends BaseResponseBody> placeSearch(
            @PageableDefault(page = 0, size = 20) Pageable pageable,
            @RequestBody @ApiParam(value = "체육 시설 검색 정보", required = true) PlaceSearchPostReq searchInfo) {

        Page<PlaceMongo> page = placeService.placeSearch(pageable, searchInfo);

        return ResponseEntity.status(200).body(
                PlaceListRes.of(
                        SUCCESS_EMAIL_NOT_FOUND.getCode(),
                        SUCCESS_EMAIL_NOT_FOUND.getMessage(),
                        page.getTotalPages(),
                        page.getTotalElements(),
                        page.getContent()
                )
        );
    }
}