package com.ssafy.api.service;

import com.querydsl.core.Tuple;
import com.ssafy.api.response.MateRes;
import com.ssafy.domain.entity.Activity;
import com.ssafy.domain.entity.Mate;
import com.ssafy.domain.entity.Member;
import com.ssafy.domain.repository.ActivityRepository;
import com.ssafy.domain.repository.MateRepositorySupport;
import com.ssafy.domain.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class MateService {

//    private final MateRepositorySupport mateRepositorySupport;
//    private final ActivityRepository activityRepository;
//    private final MemberRepository memberRepository;
//
//    public MateService(MateRepositorySupport mateRepositorySupport, ActivityRepository activityRepository, MemberRepository memberRepository){
//        this.mateRepositorySupport = mateRepositorySupport;
//        this.activityRepository = activityRepository;
//        this.memberRepository = memberRepository;
//    }


//    public Page<Mate> getRegisterList(Long activityId, Pageable pageable) {
//
//        Activity activity = activityRepository.findById(activityId).orElse(null);
//
//
//        return mateRepositorySupport.findByActivityId(activity, pageable);
//    }
//
//
    //발신한 등록 리스트
    //내 아이디로 mate 테이블에 저장된 내역 불러오기
    //메이트에 저장된 activityid로 어떤 운동인지 운동 시간 언제인지 조회 공고 낸 사람 이름 조회
//    public Page<Tuple> getSendRegisterList(Pageable pageable){
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Long memberId = Long.parseLong(authentication.getName());
//        Member member = memberRepository.findById(memberId).orElse(null);
//
//
//
//        return mateRepositorySupport.findByMemberId(member, pageable);
//    }



}
