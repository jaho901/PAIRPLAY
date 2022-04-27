package com.ssafy.api.controller;


import com.ssafy.api.request.MatePostReq;
import com.ssafy.api.response.BaseResponseBody;
import com.ssafy.api.response.MateListRes;
import com.ssafy.api.response.MateRes;
import com.ssafy.api.service.MateService;
import com.ssafy.domain.entity.Activity;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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


    //공고 생성
    @PostMapping()
    @ApiOperation(value = "공고 전체 조회", notes = "공고를 <strong>생성</strong>한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = BaseResponseBody.class),
    })
    public ResponseEntity<BaseResponseBody> createMate(@RequestPart(value = "metadata") @ApiParam(value = "공고 정보", required = true) MatePostReq mateInfo) {


        int statusCode = mateService.createMate(mateInfo);


        return createResponseEntityToStatusCode(statusCode);
    }
                                                        //이미지 일단 생략






    private ResponseEntity<BaseResponseBody> createResponseEntityToStatusCode(int statusCode) {
        switch (statusCode) {
            case 200:
                return ResponseEntity.status(200).body(BaseResponseBody.of(200, "SUCCESS"));
            case 401:
                return ResponseEntity.status(401).body(BaseResponseBody.of(401, "ACCESS DENIED"));
            default:
                return ResponseEntity.status(500).body(BaseResponseBody.of(500, "FAIL"));
        }
    }


}
