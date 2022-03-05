package com.web.imdb.service;

import com.web.imdb.entity.Customer;
import com.web.imdb.exception.CustomerAlreadyExistsException;
import com.web.imdb.exception.CustomerNotFoundException;
import com.web.imdb.repo.CustomerRepository;
import com.web.imdb.request.CustomerRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public List<Customer> getCustomerDataList() throws CustomerNotFoundException {
        return customerRepository.findAll();
    }

    public Customer updateCustomerData(CustomerRequest customer) throws CustomerNotFoundException {
        if (customerRepository.findByEmail(customer.getEmail()).isPresent()) {
            Customer user = customerRepository.findByEmail(customer.getEmail()).get();
            user.setCustomerName(customer.getCustomerName());
            user.setEmail(customer.getEmail());
            user.setPassword(customer.getPassword());
            return customerRepository.saveAndFlush(user);
        } else {
            log.info("Customer with {} doesn't exist", customer.getEmail());
            throw new CustomerNotFoundException();
        }
    }

    public Boolean deleteCustomerData(String email) throws CustomerNotFoundException {
        Boolean deleted = Boolean.FALSE;
        if (customerRepository.findByEmail(email).isPresent()) {
            Customer user = customerRepository.findByEmail(email).get();
            customerRepository.delete(user);
            deleted = Boolean.TRUE;
        } else {
            log.info("Customer with {} doesn't exist", email);
            throw new CustomerNotFoundException();
        }
        return deleted;
    }

    public Customer registerUser(CustomerRequest customerDetails) throws CustomerAlreadyExistsException {
        if (!customerRepository.findByEmail(customerDetails.getEmail()).isPresent()) {
            Customer customer = new Customer();
            customer.setEmail(customerDetails.getEmail());
            customer.setCustomerName(customerDetails.getCustomerName());
            if (customerDetails.getRole() == null || (customerDetails.getRole() != null && customerDetails.getRole().isEmpty()))
                customer.setRole("ROLE_USER");
            else customer.setRole(customerDetails.getRole());
            customer.setPassword(passwordEncoder.encode(customerDetails.getPassword()));
            return customerRepository.saveAndFlush(customer);
        } else {
            log.info("Customer with {} already exists ", customerDetails.getEmail());
            throw new CustomerAlreadyExistsException();
        }
    }
}
