package com.kpi.demo.repository;

import com.kpi.demo.models.License;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicenseRepository extends JpaRepository<License, Long> {
}
