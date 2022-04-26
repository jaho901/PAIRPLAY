package com.ssafy.api.response;


import com.ssafy.domain.entity.Activity;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ApiModel("MateListResponse")
public class MateListRes {

    public static Page<MateRes> of(Page<Activity> mateList){
        List<MateRes> list = new ArrayList<>();

        Pageable pageable = mateList.getPageable();
        long total = mateList.getTotalElements();

        mateList.forEach(mate -> {
            list.add(MateRes.of(mate));
        });

        return new PageImpl<MateRes>(list, pageable, total);
    }





}
