package com.skills.tute.controller;

import com.skills.tute.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/cache")
public class CacheController {

    @Autowired
    private CacheService service;

    @DeleteMapping("/{cacheName}")
    @PreAuthorize("hasRole('ADMIN')")
    void clearCache(@PathVariable("cacheName") String cacheName) {
        service.clearCache(cacheName);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    String[] getAllCacheName() {
        return service.getAllCacheName();
    }
}
