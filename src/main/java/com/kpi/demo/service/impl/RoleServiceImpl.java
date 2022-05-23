package com.epam.demo.service.impl;

import com.epam.demo.exception.NotFoundByIdException;
import com.epam.demo.models.Role;
import com.epam.demo.repository.RoleRepository;
import com.epam.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public boolean delete(Long roleId) {
        roleRepository.deleteById(roleId);
        return true;
    }

    @Override
    public Role findById(Long roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new NotFoundByIdException("The role can't be found by id"));
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findByRoleName(String name) {
        return roleRepository.getRoleByName(Role.RoleName.valueOf(name));
    }
}
