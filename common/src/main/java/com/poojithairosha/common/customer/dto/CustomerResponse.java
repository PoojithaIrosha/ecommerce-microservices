package com.poojithairosha.common.customer.dto;

import lombok.Builder;

@Builder
public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email,
        AddressDto address
) {
}
