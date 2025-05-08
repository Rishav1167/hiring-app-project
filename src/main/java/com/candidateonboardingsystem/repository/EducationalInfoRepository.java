package com.candidateonboardingsystem.repository;

import com.candidateonboardingsystem.domain.entity.EducationalInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationalInfoRepository extends JpaRepository<EducationalInfo, Long> {
}