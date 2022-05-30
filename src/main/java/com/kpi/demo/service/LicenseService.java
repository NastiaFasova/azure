package com.kpi.demo.service;

import com.kpi.demo.models.License;

public interface LicenseService {
    boolean isExpired();

    License save(License license);
}
