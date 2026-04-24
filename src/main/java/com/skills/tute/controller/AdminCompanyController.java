package com.skills.tute.controller;

import com.skills.tute.entity.Company;
import com.skills.tute.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/companies")
public class AdminCompanyController {

    @Autowired
    private CompanyService service;

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Company updateCompany(@RequestBody Company company) {
        return service.update(company);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCompany(@PathVariable Integer id) {
        service.deleteById(id);
    }
}
