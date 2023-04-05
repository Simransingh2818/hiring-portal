package org.credex.hiring.portal.service;

import org.credex.hiring.portal.model.Role;

import java.util.List;

public interface RoleService {
    Role createRole(Role role);

    List<Role> getAllRole();

    Role updateRole(Role role);

    Role getRoleById(int roleId);
}
