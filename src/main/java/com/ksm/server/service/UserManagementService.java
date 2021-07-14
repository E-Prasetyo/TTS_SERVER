/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.server.service;

import com.ksm.server.dto.LoginRequestDTO;
import com.ksm.server.dto.LoginResponseDTO;
import com.ksm.server.dto.RegisterDTO;
import com.ksm.server.model.Account;
import com.ksm.server.model.AccountRole;
import com.ksm.server.model.Employee;
import com.ksm.server.model.Gender;
import com.ksm.server.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ksm.server.repository.AccountRepository;
import com.ksm.server.repository.AccountRoleRepository;
import com.ksm.server.repository.EmployeeRepository;
import com.ksm.server.repository.RoleRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author aqira
 */
@Service
public class UserManagementService {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    AccountRoleRepository accountRoleRepository;
    
    public ResponseEntity<LoginResponseDTO> login(LoginRequestDTO loginData) {
        // cek email ada di db ato ngga,
        Account account = accountRepository.findByEmail(loginData.getEmail());
  
         LoginResponseDTO loginResponse = new LoginResponseDTO(
				account.getEmail(),
				account.getAccountRoles()
		 );
        // Response -> 503, 404, 200
//        
        return ResponseEntity.ok(loginResponse);
    }

    public ResponseEntity<Boolean> register(RegisterDTO registerData) throws ParseException {
       //jika employee baru sudah ada di database, maka jangan bikin employee baru.
       // jika id sudah ada
        if (employeeRepository.existsById(registerData.getId())) {
            System.out.println("Employe sudah terdaftar");
            return ResponseEntity.accepted().body(false);
        }
        //jika email sudah ada
        else if(accountRepository.existsByEmail(registerData.getEmail())){
            System.out.println("Email sudah terdaftar");
            return ResponseEntity.accepted().body(false);
        }
        else{
         //jika memang memenuhi semua pengecualian, maka save, dan return true 
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(registerData.getDate());
        
        Employee employee = new Employee(
                registerData.getId(),
                registerData.getFirstName(),
                registerData.getLastName(),
                date,
                Gender.valueOf(registerData.getGender()),
                registerData.getAddress(),
                registerData.getPhone(),
                registerData.getEmail(),
                registerData.getPassword()
        );         
         Account account = new Account(registerData.getId(), registerData.getEmail(), registerData.getPassword());
         accountRepository.save(account);
         employeeRepository.save(employee); 
        //Tambahkan Default Role  
        if(roleRepository.findAll().isEmpty()){
            setRole();
        }
        //masukin new Role(1, "employee") 
        if(registerData.getId().endsWith("A")){
            setAccRole(registerData.getId(), 1);
        }else{
            setAccRole(registerData.getId(), 2);
        }
//         setAccRole(registerData.getId(), 2);

        return ResponseEntity.accepted().body(true);
        }
    }
    public List<Employee> getAllEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }
    
    public List<AccountRole> getAllAccRole() {
        List<AccountRole> employees = accountRoleRepository.findAll();
        return employees;
    }
    
    public List<Role> getAllRole() {
        List<Role> employees = roleRepository.findAll();
        return employees;
    }
    
     public Account setAccRole(String id, Integer idr) {
        Account a = getByIdAccount(id);
        AccountRole accountRole = new AccountRole(true, getByIdRole(idr));
        accountRole.setAccount(a);
        accountRoleRepository.save(accountRole);
        AccountRole accRole = getByIdAccountRole( accountRole.getId());
        Set<AccountRole> accountRoles = new HashSet<AccountRole>();
        accountRoles.add(accRole);
        a.setAccountRoles(accountRoles);
        accountRepository.save(a);            
        return a;
    }
     
    
    public void setRole() {
        Role r = new Role("ADMIN");
        Role r2 = new Role("USER");
        List<Role> roles = new ArrayList<>();
        roles.add(r);
        roles.add(r2);
        roleRepository.saveAll(roles);
    }
    
    public Employee getByIdEmpl(String id) {
        if (employeeRepository.existsById(id)) {
            return employeeRepository.findById(id).get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Data");
    }
    
     public Account getByIdAccount(String id) {
        if (accountRepository.existsById(id)) {
            return accountRepository.findById(id).get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Data");
    }
    
     public AccountRole getByIdAccountRole(Integer id) {
        if (accountRoleRepository.existsById(id)) {
            return accountRoleRepository.findById(id).get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Data");
    }
     
    public Role getByIdRole(Integer id) {
        if (roleRepository.existsById(id)) {
            return roleRepository.findById(id).get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Data");
    }
    
}
