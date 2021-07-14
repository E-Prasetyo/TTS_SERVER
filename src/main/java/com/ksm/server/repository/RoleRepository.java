/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.server.repository;

import com.ksm.server.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author user
 */
public interface RoleRepository extends JpaRepository<Role,Integer>{

    public boolean existsByName(String id);
    
}
