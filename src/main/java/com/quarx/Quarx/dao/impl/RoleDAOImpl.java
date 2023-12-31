package com.quarx.Quarx.dao.impl;

import com.quarx.Quarx.dao.RoleDAO;
import com.quarx.Quarx.entity.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoleDAOImpl implements RoleDAO {

    private EntityManager entityManager;

    @Autowired
    public RoleDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void createRole(Role newRole) {
        entityManager.persist(newRole);
    }

    @Override
    public Optional<Role> findRole(String roleName) {
        TypedQuery<Role> roleTypedQuery = entityManager.createQuery("FROM Role WHERE role=:roleName", Role.class);
        roleTypedQuery.setParameter("roleName", roleName);
        if((roleTypedQuery.getResultList() == null)){
            return null;
        }
        Optional<Role> role = Optional.of(roleTypedQuery.getSingleResult());
        return role;
    }
}
