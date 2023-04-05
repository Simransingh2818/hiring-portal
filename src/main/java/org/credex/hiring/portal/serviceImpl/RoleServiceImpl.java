package org.credex.hiring.portal.service.impl;

import org.credex.hiring.portal.dao.RoleDao;
import org.credex.hiring.portal.model.Role;
import org.credex.hiring.portal.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    @Transactional
    public Role createRole(Role role) {
        return roleDao.createRole(role);
    }

    @Override
    @Transactional
    public List<Role> getAllRole() {
        return roleDao.getAllRole();
    }

    @Override
    @Transactional
    public Role updateRole(Role role) {
        return roleDao.updateRole(role);
    }

    @Override
    @Transactional
    public Role getRoleById(int roleId) {
        return roleDao.getRoleById(roleId);
    }
}
