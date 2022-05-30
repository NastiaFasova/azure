package com.kpi.demo.service.impl;

import com.kpi.demo.models.License;
import com.kpi.demo.repository.LicenseRepository;
import com.kpi.demo.service.LicenseService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LicenseServiceImpl implements LicenseService {
    private final LicenseRepository licenseRepository;

    public LicenseServiceImpl(LicenseRepository licenseRepository) {
        this.licenseRepository = licenseRepository;
    }

    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(LocalDate.of(2022, 12, 31));
    }

    @Override
    public License save(License license) {
        return licenseRepository.save(license);
    }
}
