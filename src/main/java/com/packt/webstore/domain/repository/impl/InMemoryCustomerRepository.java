package com.packt.webstore.domain.repository.impl;

import com.packt.webstore.domain.Customer;
import com.packt.webstore.domain.repository.CustomerRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryCustomerRepository implements CustomerRepository {

    private List<Customer> customerList = new ArrayList<>();

    public InMemoryCustomerRepository() {
        Customer customer1 = new Customer(1, "Thomas", "Warszawa");
        Customer customer2 = new Customer(2, "Eryk", "Lodz");
        Customer customer3 = new Customer(3, "Jessy", "Poznan");
        Customer customer4 = new Customer(4, "Kim", "Torun");

        customerList.add(customer1);
        customerList.add(customer2);
        customerList.add(customer3);
        customerList.add(customer4);
    }

    public List<Customer> getAllCustomers() {
        return customerList;
    }
}
