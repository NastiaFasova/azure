package com.epam.demo.repository;

import com.epam.demo.models.License;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicenseRepository extends JpaRepository<License, Long> {
}
