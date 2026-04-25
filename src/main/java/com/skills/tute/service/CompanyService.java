package com.skills.tute.service;

import com.skills.tute.entity.Company;

import java.util.List;

public interface CompanyService {

    List<Company> getCompanies(String approvalStatus);

    Company save(Company company);

    Company update(Company company);

    void deleteById(Integer id);
}
