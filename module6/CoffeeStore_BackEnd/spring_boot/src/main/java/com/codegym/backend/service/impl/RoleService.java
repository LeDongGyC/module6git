package com.codegym.backend.service.impl;

import com.codegym.backend.model.AppRole;
import com.codegym.backend.repository.IRoleRepository;
import com.codegym.backend.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService {

    @Autowired
    IRoleRepository roleRepository;

    @Override
    public List<AppRole> findAllRole() {
        return roleRepository.findAllRole();
    }

    @Override
    public void setDefaultRole(int accountId, Integer roleId) {
        roleRepository.setDefaultRole(accountId,roleId);
    }
}
