package com.skills.tute.service.impl;

import com.skills.tute.entity.Company;
import com.skills.tute.enums.ApproveStatus;
import com.skills.tute.repository.CompanyRepository;
import com.skills.tute.service.CompanyService;
import com.skills.tute.utils.StConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository repository;

    @Override
    @Cacheable(
            value = "companies"
    )
    public List<Company> getCompanies(String approvalStatus) {
        List<Company> companies;
        if (StConstant.ALL.equals(approvalStatus)) {
            companies = repository.findAll();
        } else {
            companies = repository.findByApproveStatus(ApproveStatus.valueOf(approvalStatus));
        }
        return companies;
    }

    @Override
    //@CacheEvict(value = "companies", allEntries = true)
    public Company save(Company company) {
        return repository.save(company);
    }

    @Override
    //@CacheEvict(value = "companies", allEntries = true)
    public Company update(Company company) {
        return repository.save(company);
    }

    @Override
    //@CacheEvict(value = "companies", allEntries = true)
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
