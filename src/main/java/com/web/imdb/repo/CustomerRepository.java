package com.web.imdb.repo;

import com.web.imdb.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    public Optional<Customer> findByEmail(String email);
}
