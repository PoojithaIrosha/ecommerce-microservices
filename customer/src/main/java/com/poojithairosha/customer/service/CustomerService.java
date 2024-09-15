package com.poojithairosha.customer.service;

import com.poojithairosha.common.customer.dto.CustomerRequest;
import com.poojithairosha.common.customer.dto.CustomerResponse;
import com.poojithairosha.common.customer.exception.CustomerNotFoundException;
import com.poojithairosha.customer.entity.Customer;
import com.poojithairosha.customer.mapper.CustomerMapper;
import com.poojithairosha.customer.repository.CustomerRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public String createCustomer(@Valid CustomerRequest request) {
        var customer = customerRepository.save(customerMapper.mapToEntity(request));
        return customer.getId();
    }

    public void updateCustomer(@Valid CustomerRequest request) {
        var customer = customerRepository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("Cannot update customer:: No Customer found with ID:: %s", request.id())
                ));
        mergeCustomer(customer, request);
        customerRepository.save(customer);
    }

    private void mergeCustomer(Customer customer, CustomerRequest request) {
        if (StringUtils.isNotBlank(request.firstName())) {
            customer.setFirstName(request.firstName());
        }
        if (StringUtils.isNotBlank(request.lastName())) {
            customer.setLastName(request.lastName());
        }
        if (StringUtils.isNotBlank(request.email())) {
            customer.setEmail(request.email());
        }
        if (request.address() != null) {
            customer.setAddress(customerMapper.mapAddressToEntity(request.address()));
        }
    }

    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll().stream().map(customerMapper::mapToResponse).toList();
    }

    public Boolean existsById(String customerId) {
        return customerRepository.findById(customerId).isPresent();
    }

    public CustomerResponse findById(String customerId) {
        return customerRepository.findById(customerId).map(customerMapper::mapToResponse)
                .orElseThrow(() -> new CustomerNotFoundException(format("No customer found with ID:: %s", customerId)));
    }

    public void delete(String customerId) {
        customerRepository.deleteById(customerId);
    }
}
