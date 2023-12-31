package com.quarx.Quarx.controller;

import com.quarx.Quarx.entity.Role;
import com.quarx.Quarx.service.RoleService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {
    private RoleService roleService;

    @Autowired
    private RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @PostConstruct
    public void createRoles(){

        Role role1 = new Role("ROLE_ADMIN");
        roleService.createRole(role1);
    }
}
