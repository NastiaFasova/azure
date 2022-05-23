package com.epam.demo.service;

import com.epam.demo.models.License;

public interface LicenseService {
    boolean isExpired();

    License save(License license);
}
