package com.learn.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.learn.graphql.customers.Customer;
import com.learn.graphql.customers.data.CustomerModel;
import com.learn.graphql.customers.data.CustomerRepository;
import org.springframework.stereotype.Component;

@Component
public class QueryResolver implements GraphQLQueryResolver {

    private CustomerRepository customerRepository;

    public QueryResolver(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer customerById(Long id) {
        return customerRepository
                .findById(id)
                .map(this::modelToGraphQL)
                .orElse(null);
    }

    private Customer modelToGraphQL(CustomerModel customerModel) {
        Customer customer = new Customer();
        customer.setId(customerModel.getId());
        customer.setName(customerModel.getName());
        customer.setEmail(customerModel.getEmail());

        return customer;
    }
}
