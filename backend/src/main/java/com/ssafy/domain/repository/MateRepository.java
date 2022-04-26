package com.ssafy.domain.repository;

import com.ssafy.domain.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MateRepository extends JpaRepository<Activity, Long> {


}
