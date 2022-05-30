package com.kpi.demo.service;

import com.kpi.demo.models.Role;
import java.util.List;

public interface RoleService {
    Role save(Role role);

    boolean delete(Long roleId);

    Role findById(Long roleId);

    List<Role> findAll();

    Role findByRoleName(String user);
}
