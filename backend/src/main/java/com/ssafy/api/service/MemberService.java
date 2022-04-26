package com.ssafy.api.service;

import com.ssafy.api.request.MemberSignupPostReq;
import com.ssafy.domain.entity.Member;
import com.ssafy.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("memberService")
public class MemberService {

    private final MemberRepository memberRepository;

//    private final JavaMailSender javaMailSender;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member signup(MemberSignupPostReq memberInfo) {
        String email = memberInfo.getEmail();
        String nickname = memberInfo.getNickname();
//        String password = passwordEncoder.encode(memberInfo.getPassword()); // 컨트롤러에서 암오화 해서 넘겨오기

        Member member = Member.builder()
                .email(email)
                .nickname(nickname)
//                .password(password)
                .build();
        return memberRepository.save(member);
    }

//    public Member afterSignup(MemberSignupPutReq memberInfo) {
//
//        Member member = Member.builder()
//                .email(email)
//                .nickname(nickname)
//                .password(password)
//                .build();
//        return memberRepository.save(member);
//    }

    public Member getMemberById(Long memberId) {
        // 디비에 유저 정보 조회 (memberId 를 통한 조회).
        return memberRepository.findById(memberId).orElse(null);
    }

    public Member getMemberByEmail(String memberEmail) {
        // 디비에 유저 정보 조회 (userEmail 를 통한 조회).
        return memberRepository.findByEmail(memberEmail).orElse(null);
    }

    public Member getMemberByNickname(String memberNickname) {
        // 디비에 유저 정보 조회 (userEmail 를 통한 조회).
        return memberRepository.findByNickname(memberNickname).orElse(null);
    }

//    @Override
//    public boolean sendMail(String email) {
//        // 아이디로 메일, 비밀번호 가져오기
//        User user = this.getUserByEmail(email);
//
//        System.out.println(user);
//
//        // 메일 보내기
//        if (user != null) {
//            // 메세지 내용이 너무 심플함. 좀 더 제목이랑 내용 추가 시키면 좋을 듯
//            SimpleMailMessage simpleMessage = new SimpleMailMessage();
//            simpleMessage.setTo(user.getEmail());
//            simpleMessage.setSubject(user.getNickname() + "님의 임시 비밀번호");
//            String tmpPassword = PasswordUtil.getRandomPassword(); // 임시비밀번호
//            String tmpJwtToken = passwordEncoder.encode(tmpPassword); // 임시비밀번호의 jwtToken // 임시비밀번호 생성 암호화 컨트롤러에서 아니면 패스워드 유틸에서
//            // password update 구문
//            user.resetPassword(tmpJwtToken);
//            memberRepository.save(user);
//            simpleMessage.setText(user.getNickname() + "님의 임시비밀번호는 " + tmpPassword + " 입니다.");
//            javaMailSender.send(simpleMessage);
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    @Override
//    public void deleteUserById(Long memberId) {
//        Member member = getMemberById(memberId);
//
//        memberRepository.delete(member);
//    }
}