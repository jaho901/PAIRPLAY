package com.ssafy.api.controller;


import com.ssafy.api.response.BaseResponseBody;
import com.ssafy.api.response.MateListRes;
import com.ssafy.api.response.MateRes;
import com.ssafy.api.service.MateService;
import com.ssafy.domain.entity.Activity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "메이트 API", tags = {"MATE"})
@RestController
@RequestMapping("/api/v1/mates")
public class MateController {

    private final MateService mateService;

    public MateController(MateService mateService) {
        this.mateService = mateService;
    }


    //공고 전체조회
    @GetMapping()
    @ApiOperation(value = "공고 전체 조회", notes = "공고를 <strong>전체조회</strong>한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = BaseResponseBody.class),
    })
    public ResponseEntity<Page<MateRes>> getMateList(@PageableDefault(page = 0, size = 6) Pageable pageable){

        Page<Activity> mates = mateService.getMateList(pageable);

        if(mates != null){
            return ResponseEntity.status(500).body(MateListRes.of(mates));
        }else {
            return ResponseEntity.status(500).body(null);
        }
    }



    //공고 카테고리 조회





}
