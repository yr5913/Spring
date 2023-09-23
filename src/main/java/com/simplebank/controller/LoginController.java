package com.simplebank.controller;

import com.simplebank.entity.Customer;
import com.simplebank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer/")
public class LoginController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CustomerRepository customerRepository;

    @RequestMapping("register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer) {
        Customer saved = null;
        ResponseEntity<String> response = null;
        try {
            //hashig password
            customer.setPwd(passwordEncoder.encode(customer.getPwd()));
            saved = customerRepository.save(customer);
            response = ResponseEntity.status(HttpStatus.CREATED).body("User is successfully registered");
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An exception occured due to " + e.getMessage());
        }
        return response;
    }

}
