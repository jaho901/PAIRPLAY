package com.ssafy.api.response;

import com.querydsl.core.Tuple;
import com.ssafy.domain.entity.Mate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ApiModel("Mate Send List Response")
public class MateSendListRes extends BaseResponseBody{

//
//    @ApiModelProperty(name = "mate send list")
//    Page<MateSendRes> list;

//    public static MateSendListRes of(Page<Tuple> mateSendList, Integer statusCode, String message){



//        MateSendListRes mateSendListRes = new MateSendListRes();
//
//        mateSendListRes.setCode(statusCode);
//        mateSendListRes.setMessage(message);
//
//        List<MateSendRes> list = new ArrayList<>();
//
//        Pageable pageable = mateSendList.getPageable();
//        long total = mateSendList.getTotalElements();
//
//        mateSendList.forEach(mate -> {
//            list.add(MateSendRes.of(mate));
//        });
//
//        mateSendListRes.setList(new PageImpl<MateSendRes>(list, pageable, total));
//
//        return mateSendListRes;
//    }

}
