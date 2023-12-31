package com.quarx.Quarx.service.impl;

import com.quarx.Quarx.dao.RoleDAO;
import com.quarx.Quarx.entity.Role;
import com.quarx.Quarx.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleDAO roleDAO;
    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO){
        this.roleDAO = roleDAO;
    }
    @Override
    public void createRole(Role newRole) {
        roleDAO.createRole(newRole);
//        if(roleDAO.findRole(newRole.getRole()) == null){
//            roleDAO.createRole(newRole);
//            }
    }

    @Override
    public void grantRole() {

    }
}
