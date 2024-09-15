package com.poojithairosha.customer.mapper;

import com.poojithairosha.common.customer.dto.AddressDto;
import com.poojithairosha.common.customer.dto.CustomerRequest;
import com.poojithairosha.common.customer.dto.CustomerResponse;
import com.poojithairosha.customer.entity.Address;
import com.poojithairosha.customer.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer mapToEntity(CustomerRequest request) {
        if (request == null) {
            return null;
        }

        return Customer.builder()
                .id(request.id())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .address(mapAddressToEntity(request.address()))
                .build();
    }

    public CustomerResponse mapToResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .address(mapAddressToResponse(customer.getAddress()))
                .build();
    }

    public AddressDto mapAddressToResponse(Address address) {
        return AddressDto.builder()
                .street(address.getStreet())
                .houseNumber(address.getHouseNumber())
                .zipCode(address.getZipCode())
                .build();
    }

    public Address mapAddressToEntity(AddressDto addressDto) {
        if (addressDto == null) {
            return null;
        }

        return Address.builder()
                .street(addressDto.street())
                .houseNumber(addressDto.houseNumber())
                .zipCode(addressDto.zipCode())
                .build();
    }
}
