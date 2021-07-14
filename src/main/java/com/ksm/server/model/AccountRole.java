/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author aqira
 */
@Entity
@Table(name = "account_role")
public class AccountRole {

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 2)
    private Integer id;

    @Column(name = "is_deleted")
    private boolean isDeleted;

//     @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "role", nullable = false)
    private Role role;

    

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "account", nullable = false)
    private Account account;

    public AccountRole(boolean isDeleted, Role role) {
        this.isDeleted = isDeleted;
        this.role = role;
    }

    public AccountRole() {
    }
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    
    public boolean isIsDeleted() {
        return isDeleted;
    } 
    
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
