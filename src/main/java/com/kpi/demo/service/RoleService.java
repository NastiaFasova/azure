package com.epam.demo.service;

import com.epam.demo.models.Role;
import java.util.List;

public interface RoleService {
    Role save(Role role);

    boolean delete(Long roleId);

    Role findById(Long roleId);

    List<Role> findAll();

    Role findByRoleName(String user);
}
