package com.web.imdb.controller;

import com.web.imdb.entity.Customer;
import com.web.imdb.request.CustomerRequest;
import com.web.imdb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/get-users")
    public ResponseEntity getUserData() {
        List<Customer> users = userService.getCustomerDataList();
        System.out.println("users " + users);
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @DeleteMapping("/delete-user/{email}")
    public ResponseEntity deleteUserData(@PathVariable String email) {
        Boolean deleteCustomerData = userService.deleteCustomerData(email);
        return ResponseEntity.status(HttpStatus.OK).body(NO_CONTENT);
    }

    @PutMapping("/update-user")
    public ResponseEntity updateUserData(@RequestBody CustomerRequest customerRequest) {
        Customer user = userService.updateCustomerData(customerRequest);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody CustomerRequest customerDetails) {
        Customer user = userService.registerUser(customerDetails);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
