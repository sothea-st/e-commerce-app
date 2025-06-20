package com.alibou.ecomerce.handle;
import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {

}