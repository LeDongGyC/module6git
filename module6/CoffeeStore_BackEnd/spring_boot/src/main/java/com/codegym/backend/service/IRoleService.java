package com.codegym.backend.service;

import com.codegym.backend.model.AppRole;

import java.util.List;

public interface IRoleService {
    List<AppRole> findAllRole();
    void setDefaultRole(int accountId, Integer roleId);
}
