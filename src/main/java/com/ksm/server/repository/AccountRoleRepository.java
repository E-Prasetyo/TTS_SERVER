/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.server.repository;

import com.ksm.server.model.AccountRole;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author user
 */
public interface AccountRoleRepository extends JpaRepository<AccountRole, Integer>{
    
}
