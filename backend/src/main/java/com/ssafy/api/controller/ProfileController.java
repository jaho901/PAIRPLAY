package com.ssafy.api.controller;

import com.ssafy.api.response.BaseResponseBody;
import com.ssafy.api.response.ProfileRes;
import com.ssafy.api.service.MemberService;
import com.ssafy.api.service.ProfileService;
import com.ssafy.domain.entity.Member;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "프로필 API", tags = {"Profile"})
@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileController {

    private final MemberService memberService;
    private final ProfileService profileService;

    public ProfileController (MemberService memberService, ProfileService profileService) {
        this.memberService = memberService;
        this.profileService = profileService;
    }

    @GetMapping("/{memberId}")
    @ApiOperation(value = "유저 프로필 조회", notes = "전달 받은 <string>Id</string>로 유저 프로필을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = ProfileRes.class),
    })
    public ResponseEntity<? extends ProfileRes> searchProfile(@PathVariable Long memberId) {

        Member member = memberService.getMemberById(memberId);

        if (member != null)
            return ResponseEntity.status(200).body(ProfileRes.of(member));
        return ResponseEntity.status(500).body(null);
    }

    // 이미지 저장 방식에 따라 PutMapping의 데이터 받는 방식이 변경될 것
    
    @DeleteMapping("")
    @ApiOperation(value = "회원 탈퇴", notes = "<string>JWT토큰</string>의 ID에 해당하는 회원이 탈퇴한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = ProfileRes.class),
    })
    public ResponseEntity<? extends BaseResponseBody> withdrawMember() {
        profileService.withdrawMember();
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }
}
