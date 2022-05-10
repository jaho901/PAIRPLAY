package com.ssafy.domain.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.api.response.MateRes;
import com.ssafy.domain.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class MateRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    public MateRepositorySupport(JPAQueryFactory jpaQueryFactory){
        this.jpaQueryFactory = jpaQueryFactory;
    }

    QMate mate = QMate.mate;
    QActivity activity = QActivity.activity;
    /**
     * mate 수신 조회
     */
//    public Page<Mate> findByActivityId(Activity activity, Pageable pageable){
//
//        QueryResults<Mate> mates = jpaQueryFactory
//                .select(mate)
//                .from(mate)
//                .where(mate.activityId.eq(activity))
//                .orderBy(mate.id.desc())
//                .limit(pageable.getPageSize())
//                .offset(pageable.getOffset())
//                .fetchResults();
//
//        if(mates == null) return Page.empty();
//
//
//        return new PageImpl<Mate>(mates.getResults(), pageable, mates.getTotal());
//    }


    /**
     * mate 발신 조회
     */
//    public Page<Tuple> findByMemberId(Member member, Pageable pageable){
//        QueryResults<Tuple> mates = jpaQueryFactory
//                .select(mate, activity)
//                .from(mate)
//                .join(mate.activityId, activity)
//                .on(mate.activityId.id.eq(activity.id))
//                .where(mate.memberId.id.eq(member.getId()))
//                .orderBy(mate.id.desc())
//                .limit(pageable.getPageSize())
//                .offset(pageable.getOffset())
//                .fetchResults();
//
//        if(mates == null) return Page.empty();
//
//
//        return new PageImpl<MateRes>(mates, pageable, mates.getTotal());
//    }



}
