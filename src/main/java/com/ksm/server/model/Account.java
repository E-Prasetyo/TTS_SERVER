/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author aqira
 */
@Entity
@Table(name = "account")
public class Account {//extends Employee {

    @Id
    @Basic(optional = false)
    @Column(name = "id", length = 5)
    private String id;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "password", length = 50)
    private String password;

    @Column(name = "token", length = 50)
    private String token;

    @Column(name = "is_deleted")
    private boolean isDeleted;
//
//    @OneToOne()
//    @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")//---
//    @OneToOne
//    @MapsId
//    @JoinColumn(name = "id")//-----
//    @OneToOne(mappedBy = "account")
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Employee employee;

//    @JsonIgnore
    @OneToMany(mappedBy = "account")
    private Set<AccountRole> accountRoles;
    
    public Set<AccountRole> getAccountRoles() {
        return accountRoles;
    }

    public void setAccountRoles(Set<AccountRole> accountRoles) {
        this.accountRoles = accountRoles;
    }
    
    public Account() {
    }

    //untuk register akun baru
    public Account(String id,String email, String password) {
        //memasukkan ke yang employee
//        super(id, firstName, lastName, address, phone);
        //memasukkan ke yang account
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

}
