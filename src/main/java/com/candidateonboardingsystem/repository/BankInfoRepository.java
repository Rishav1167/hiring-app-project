package com.candidateonboardingsystem.repository;



import com.candidateonboardingsystem.domain.entity.BankInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankInfoRepository extends JpaRepository<BankInfo, Long> {
}