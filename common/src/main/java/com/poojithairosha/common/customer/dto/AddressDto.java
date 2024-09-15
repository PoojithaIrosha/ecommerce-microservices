package com.poojithairosha.common.customer.dto;

import lombok.Builder;

@Builder
public record AddressDto(
        String street,
        String houseNumber,
        String zipCode
) {
}
