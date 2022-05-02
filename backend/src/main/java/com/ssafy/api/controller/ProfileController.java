package com.ssafy.api.controller;

import com.ssafy.api.request.ProfilePasswordPostReq;
import com.ssafy.api.request.ProfilePutReq;
import com.ssafy.api.response.BaseResponseBody;
import com.ssafy.api.response.CalendarActivityRes;
import com.ssafy.api.response.ProfileRes;
import com.ssafy.api.service.MemberService;
import com.ssafy.api.service.ProfileService;
import com.ssafy.common.handler.CustomException;
import com.ssafy.domain.entity.Activity;
import com.ssafy.domain.entity.Member;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import static com.ssafy.common.statuscode.CommonCode.EMPTY_REQUEST_VALUE;
import static com.ssafy.common.statuscode.ProfileCode.*;

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
    @GetMapping("")
    @ApiOperation(value = "테스트", notes = "테스트")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
    })
    public void test() {
        System.out.println("Hello Test");
    }

    // 유저 프로필 조회
    @GetMapping("/{memberId}")
    @ApiOperation(value = "유저 프로필 조회", notes = "전달 받은 <string>Id</string>로 유저 프로필을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "유저 프로필 조회에 성공했습니다.", response = ProfileRes.class),
    })
    public ResponseEntity<? extends ProfileRes> searchProfile(@PathVariable Long memberId) {
        Member member = memberService.getMemberById(memberId);

        return ResponseEntity.status(200).body(
                ProfileRes.of(
                    member,
                    SUCCESS_SEARCH_PROFILE.getCode(),
                    SUCCESS_SEARCH_PROFILE.getMessage()
                )
        );
    }

    // Profile Update
    @PutMapping("")
    @ApiOperation(value = "유저 프로필 수정", notes = "<string>JWT토큰</string>의 ID에 해당하는 회원 정보를 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "유저 프로필 수정에 성공했습니다.", response = ProfileRes.class),
    })
    public ResponseEntity<? extends BaseResponseBody> updateProfile(@RequestBody ProfilePutReq profilePutReq) {
        profileService.updateMemberProfile(profilePutReq);

        return ResponseEntity.status(200).body(
                BaseResponseBody.of(
                        SUCCESS_UPDATE_PROFILE.getCode(),
                        SUCCESS_UPDATE_PROFILE.getMessage()
                )
        );
    }
    
    // Profile Image Update
    @PutMapping("/profileImage")
    @ApiOperation(value = "유저 프로필사진 수정", notes = "<string>JWT토큰</string>의 ID에 해당하는 회원 프로필 사진을 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "유저 프로필 이미지 수정에 성공했습니다.", response = ProfileRes.class),
    })
    public ResponseEntity<? extends BaseResponseBody> updateProfileImage(MultipartHttpServletRequest request) {
        profileService.updateMemberProfileImage(request);
        return ResponseEntity.status(200).body(
                BaseResponseBody.of(
                        SUCCESS_UPDATE_PROFILE_IMAGE.getCode(),
                        SUCCESS_UPDATE_PROFILE_IMAGE.getMessage()
                )
        );
    }

    // Password Update
    @PostMapping("")
    @ApiOperation(value = "유저 비밀번호 수정", notes = "<string>JWT토큰</string>의 ID에 해당하는 회원 비밀번호를 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "비밀번호 변경에 성공했습니다.", response = ProfileRes.class),
    })
    public ResponseEntity<? extends BaseResponseBody> updatePassword(@RequestBody ProfilePasswordPostReq profilePasswordPostReq) {
        // 순환 참조 문제 해결 때문에 컨트롤러에서 암호화해서 넣기
        String password = profilePasswordPostReq.getPassword();

        if ("".equals(password) || password == null)
            throw new CustomException(EMPTY_REQUEST_VALUE);

        profilePasswordPostReq.setPassword( passwordEncoder.encode(password) );

        profileService.updateMemberPassword(profilePasswordPostReq);
        return ResponseEntity.status(200).body(
                BaseResponseBody.of(
                        SUCCESS_UPDATE_PASSWORD.getCode(),
                        SUCCESS_UPDATE_PASSWORD.getMessage()
                )
        );
    }
    
    
    // 회원 탈퇴
    @DeleteMapping("")
    @ApiOperation(value = "회원 탈퇴", notes = "<string>JWT토큰</string>의 ID에 해당하는 회원이 탈퇴한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "회원 비활성화에 성공했습니다.", response = ProfileRes.class),
    })
    public ResponseEntity<? extends BaseResponseBody> withdrawMember() {
        profileService.withdrawMember();
        return ResponseEntity.status(200).body(
                BaseResponseBody.of(
                        SUCCESS_WITHDRAW_MEMBER.getCode(),
                        SUCCESS_WITHDRAW_MEMBER.getMessage()
                )
        );
    }


    // 달력 조회
    // 일정한 기간 중에서, 내가 참여한 모든 Activity에 대한 정보를 불러오는 것
    // Mate 테이블에 먼저 조회를 해서 내가 참여한 Activity list를 구한다
    // 구해진 Activity list를 사용해서 meet_dt 범위가 우리가 미리 정해둔 달력에 표현되는 일정 범위안의 Activity를 조회
    @GetMapping("/calendar")
    @ApiOperation(value = "달력 조회", notes = "<string>JWT토큰</string>의 ID를 사용하여 <string>일정기간</string> 내의 활동 목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "달력 조회에 성공했습니다.", response = ProfileRes.class),
    })
    public ResponseEntity<CalendarActivityRes> searchCalendar() {
        List<Activity> list = profileService.searchCalendar();

        return ResponseEntity.status(200).body(
                CalendarActivityRes.of(
                        SUCCESS_SEARCH_CALENDAR.getCode(),
                        SUCCESS_SEARCH_CALENDAR.getMessage(),
                        list
                )
        );
    }










    // 내가 만든 Activity라는 것
    // Activity에서 memberId로 search
    // 또한, 해당 Activity에 신청을 한 신청자들에 대한 정보를 같이 넘겨준다
    // 신청자들을 수락/거절 할 수 있도록 할 수 있는 기능 존재
    // 현재 수락된 사람을 강퇴할 수 있는 기능 존재


    // 남이 만든 Activity라는 것
    // Mate에서 memberId로 search 한 다음, 내가 만든 것이 아닌 Activity를 의미할 것
    // 남이 만든 Activity에는 2가지 상태가 있을 수 있다
    // 현재 신청 수락 완료된 상태, 아니면 신청을 넣어만 놓은 상태
    // 해당 상태를 구분해서 볼 수 있어야 할 것



    // 이미 종료된 Activity 역시 조회할 수 있을 것
    // 해당 내용이 달력에서 표시하는 기능일 것

    // 달력 조회 -> activity에 대한 list와 각 activity를 같이 한 mate에 대한 프로필 사진 + 아이디 같은 것
    //// 달력에 표시되는 것과, 해당 날짜에 대한 활동 이력들에 대한 리스트일 것

    // 예약 체육시설 조회
    //// 체육시설 예약 기능이 생기면, 해당 예약이 생성될 것
    //// 해당 테이블을 읽어오기만 하면 될듯

}
