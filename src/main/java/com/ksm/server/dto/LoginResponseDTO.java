/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.server.dto;

import com.ksm.server.model.AccountRole;
import java.util.Set;

/**
 *
 * @author aqira
 */
public class LoginResponseDTO {

    private String email;
    private Set<String> roles; //employee, admin, HR

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(String email, Set<AccountRole> accountRoles) {
        this.email = email;
        for (AccountRole accountRole : accountRoles) {
            roles.add(accountRole.getRole().getName());
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
    
    
    /**
     * {
     * "email" : "aqira.mudjiarto@mii.co.id"
     * "roles" : ["employee", "HR"]
     * }
     **/
}
