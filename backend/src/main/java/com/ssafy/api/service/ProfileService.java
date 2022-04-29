package com.ssafy.api.service;

import com.ssafy.api.request.ProfilePasswordPostReq;
import com.ssafy.api.request.ProfilePutReq;
import com.ssafy.common.handler.CustomException;
import com.ssafy.common.util.JwtTokenUtil;
import com.ssafy.domain.entity.Member;
import com.ssafy.domain.repository.MemberRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import static com.ssafy.common.statuscode.ProfileCode.FAIL_MEMBER_NOT_FOUND;

@Service("profileService")
public class ProfileService {

    private final MemberRepository memberRepository;

    public ProfileService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

//    public Member getMemberProfile(Long memberId) {
//        // DB에 유저 정보 조회
//        return memberRepository.findById(memberId).orElse(null);
//    }

    // JWT 토큰의 memberId 주인의 Profile Update
    public void updateMemberProfile(ProfilePutReq profilePutReq) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = Long.parseLong(authentication.getName());

        Member member = memberRepository.findById(memberId).orElse(null);

        if (member == null)
            throw new CustomException(FAIL_MEMBER_NOT_FOUND);

        if (profilePutReq.getNickname() == null) profilePutReq.setNickname(member.getNickname());
        if (profilePutReq.getName() == null) profilePutReq.setName(member.getName());
        if (profilePutReq.getGender() == -1) profilePutReq.setGender(member.getGender());
        if (profilePutReq.getBirthDt() == null) profilePutReq.setBirthDt(member.getBirthDt());
        if (profilePutReq.getAddress() == null) profilePutReq.setAddress(member.getAddress());
        if (profilePutReq.getPhone() == null) profilePutReq.setPhone(member.getPhone());
        if (profilePutReq.getDescription() == null) profilePutReq.setDescription(member.getDescription());

        member.profileUpdate(profilePutReq);
        memberRepository.save(member);
    }

    public void updateMemberProfileImage(MultipartHttpServletRequest request) {
        if (request != null) {
            // do something
        } else {
            // DB의 profile image url을 default 값으로 변경
        }
    }

    // JWT 토큰의 memberId 주인의 Password Update
    public void updateMemberPassword(ProfilePasswordPostReq profilePasswordPostReq) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = Long.parseLong(authentication.getName());

        Member member = memberRepository.findById(memberId).orElse(null);

        if (member == null)
            throw new CustomException(FAIL_MEMBER_NOT_FOUND);

        member.passwordUpdate(profilePasswordPostReq.getPassword());
        memberRepository.save(member);
    }

    // 회원 탈퇴시, enable 를 false로 수정
    public void withdrawMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long memberId = Long.parseLong(authentication.getName());

        Member member = memberRepository.findById(memberId).orElse(null);

        if (member == null)
            throw new CustomException(FAIL_MEMBER_NOT_FOUND);

        member.memberEnableUpdate(false);
        memberRepository.save(member);
    }


}
