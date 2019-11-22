package com.asidun.SpringDataJdbc;

import com.asidun.SpringDataJdbc.entity.Customer;
import com.asidun.SpringDataJdbc.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.AutoConfigureDataJdbc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
@AutoConfigureDataJdbc
class SpringDataJdbcApplicationTests {

    @Autowired
    CustomerRepository customerRepo;

    @Test
    @Transactional
    public void createSimpleCustomer() {

        Customer customer = new Customer();
        customer.dob = LocalDate.of(1904, 5, 14);
        customer.firstName = "Albert";

        Customer saved = customerRepo.save(customer);

        assertThat(saved.id).isNotNull();

        saved.firstName = "Hans Albert";

        customerRepo.save(saved);

        Optional<Customer> reloaded = customerRepo.findById(saved.id);

        assertThat(reloaded).isNotEmpty();

        assertThat(reloaded.get().firstName).isEqualTo("Hans Albert");
    }

}
