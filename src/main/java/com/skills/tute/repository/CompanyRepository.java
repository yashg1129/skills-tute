package com.skills.tute.repository;

import com.skills.tute.entity.Company;
import com.skills.tute.enums.ApproveStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    Company findByName(String name);
    List<Company> findByApproveStatus(ApproveStatus approveStatus);
}
