package com.quarx.Quarx.dao;

import com.quarx.Quarx.entity.Role;

import java.util.Optional;

public interface RoleDAO{
    public void createRole(Role role);
    public Optional<Role> findRole(String roleName);
}
