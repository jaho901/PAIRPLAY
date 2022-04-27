package com.ssafy.api.controller;

import com.ssafy.api.request.ProfilePutReq;
import com.ssafy.api.response.BaseResponseBody;
import com.ssafy.api.response.ProfileRes;
import com.ssafy.api.service.MemberService;
import com.ssafy.api.service.ProfileService;
import com.ssafy.common.handler.CustomException;
import com.ssafy.domain.entity.Member;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import static com.ssafy.common.statuscode.CommonCode.EMPTY_REQUEST_VALUE;

@Api(value = "프로필 API", tags = {"Profile"})
@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileController {

    private final MemberService memberService;
    private final ProfileService profileService;
    private final PasswordEncoder passwordEncoder;

    public ProfileController (MemberService memberService, ProfileService profileService, PasswordEncoder passwordEncoder) {
        this.memberService = memberService;
        this.profileService = profileService;
        this.passwordEncoder = passwordEncoder;
    }

    // 테스트
    @GetMapping("/test")
    @ApiOperation(value = "테스트", notes = "테스트")
    public void test() {
        System.out.println("Hello Test");
    }

    // 유저 프로필 조회
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
    @PutMapping("")
    @ApiOperation(value = "유저 프로필 수정", notes = "<string>JWT토큰</string>의 ID에 해당하는 회원 정보를 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = ProfileRes.class),
    })
    public ResponseEntity<? extends BaseResponseBody> updateProfile(@ModelAttribute ProfilePutReq profilePutReq, MultipartHttpServletRequest request) {
        // 순환 참조 문제 해결 때문에 컨트롤러에서 암호화해서 넣기
        String password = profilePutReq.getPassword();
        profilePutReq.setPassword( passwordEncoder.encode(password) );

        profileService.updateMemberProfile(profilePutReq, request);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }
    
    // 회원 탈퇴
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
