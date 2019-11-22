package com.asidun.SpringDataJdbc.repository;

import com.asidun.SpringDataJdbc.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
