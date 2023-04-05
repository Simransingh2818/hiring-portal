package org.credex.hiring.portal.controller;

import org.credex.hiring.portal.dao.RoleDao;
import org.credex.hiring.portal.model.Role;
import org.credex.hiring.portal.model.Users;
import org.credex.hiring.portal.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class MasterController {

    @Autowired
    private RoleService roleService;
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/add")
    public Role createRole(@RequestBody Role role) {

        return roleService.createRole(role);
    }
    @GetMapping("/get")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Role> getAllRole() {

        return roleService.getAllRole();
    }
    @PutMapping("/update")
    @CrossOrigin(origins = "http://localhost:4200")
    public Role updateUser( @RequestBody Role role) {

        return roleService.updateRole(role);
    }
    @GetMapping("/getById/{roleId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Role getRoleById(@PathVariable int roleId) {

        return roleService.getRoleById(roleId);
    }


}
