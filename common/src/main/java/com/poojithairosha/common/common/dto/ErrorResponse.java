package com.poojithairosha.common.common.dto;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}
