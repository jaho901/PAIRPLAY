package com.ssafy.api.service;

import com.ssafy.api.request.MatePostReq;
import com.ssafy.domain.entity.Activity;
import com.ssafy.domain.repository.MateRepository;
import com.ssafy.domain.repository.MateRepositorySupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MateService {


    private final MateRepository mateRepository;
    private final MateRepositorySupport mateRepositorySupport;

    public MateService(MateRepository mateRepository, MateRepositorySupport mateRepositorySupport){
        this.mateRepository = mateRepository;
        this.mateRepositorySupport = mateRepositorySupport;
    }

    public Page<Activity> getMateList(Pageable pageable) {

        Page<Activity> mates = mateRepositorySupport.findAll(pageable);

        if(mates.isEmpty()) return null;

        return mates;
    }

    
    
    
    //메이트 등록
    public int createMate(MatePostReq mateInfo) {

        Activity activity = Activity.builder()
                .categoryId(mateInfo.getCategoryId())
                .isTeam(mateInfo.isTeam())
                .createId(mateInfo.getCreateId())
                .meetDt(mateInfo.getMeetDt())
                .title(mateInfo.getTitle())
                .description(mateInfo.getDescription())
                .build();
        if(activity == null) return 500;

        mateRepository.save(activity);
        return 200;
    }
}
