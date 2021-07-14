/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.server.controller;

import com.ksm.server.dto.LoginRequestDTO;
import com.ksm.server.dto.LoginResponseDTO;
import com.ksm.server.dto.RegisterDTO;
import com.ksm.server.model.Account;
import com.ksm.server.model.AccountRole;
import com.ksm.server.model.Employee;
import com.ksm.server.model.Role;
import com.ksm.server.service.UserManagementService;
import java.text.ParseException;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author aqira
 */
@RestController
@RequestMapping("/api")
public class UserManagementController {

    @Autowired
    UserManagementService service;

    @PostMapping("/login") //email + password
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
        return service.login(loginRequest);
    }
    
    @GetMapping("/emp")
    public List<Employee> getAllEmp(){
        List<Employee> employees= service.getAllEmployee();
        return employees;
    }
    
    @GetMapping("/AccRole")
    public List<AccountRole> getAllAccRole(){
        List<AccountRole> employees= service.getAllAccRole();
        return employees;
    }
    
    @GetMapping("/role") //localhost:8088/api/person/1
    public List<Role> getRole(){ 
        return service.getAllRole();
    }
    
    @GetMapping("/{id}") //localhost:8088/api/person/1
    public Account getById(@PathVariable String id){ 
        return service.getByIdAccount(id);
    }
    
    @GetMapping("/{id}/AccountRole") //localhost:8088/api/person/1
    public Set<AccountRole> getById(@PathVariable Integer id){ 
        return (Set<AccountRole>) service.getByIdAccountRole(id);
    }

    @PostMapping("/register")
    public ResponseEntity<Boolean> register(@RequestBody RegisterDTO registerData) throws ParseException {
        return service.register(registerData);
    }

}
