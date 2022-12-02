package com.example.BridgeAndCoCursach.API;

import com.example.BridgeAndCoCursach.APIRepository.AccountAPIRepository;
import com.example.BridgeAndCoCursach.Models.Account;

import com.example.BridgeAndCoCursach.Securing.Sha512PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/account/")
public class APIAccountController {

    @Autowired
    private PasswordEncoder passwordEncoder;

private final AccountAPIRepository repository;

    APIAccountController(AccountAPIRepository repository) {
        this.repository = repository;
    }
    @GetMapping("/accounts")
    List<Account> all() {
        return repository.findAll();
    }
    @GetMapping("/{id}")
    Account one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow();
    }

    @PutMapping("/{id}")
    Account replaceEmployee(@RequestBody Account newEmployee, @PathVariable Long id) {

        return repository.findById(id)
                .map(employee -> {
                    employee.setPassword(passwordEncoder.encode(newEmployee.getPassword()));
                    employee.setRole(newEmployee.getRole());
                    employee.setUsername(newEmployee.getUsername());
                    employee.setActive(newEmployee.getActive());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
    }
}



