package com.web.imdb.service;

import com.web.imdb.entity.Customer;
import com.web.imdb.exception.CustomerNotFoundException;
import com.web.imdb.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomerDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(email).orElseThrow(
                () -> new CustomerNotFoundException("Customer doesn't exist with this email ")
        );
        return new User(customer.getEmail(), customer.getPassword(), new ArrayList<>());
    }
}
