package com.back.ecomm.record;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserLoginRecord(
        @NotNull(message="username can not be null")
        @NotBlank(message = "username can not be empty")
        @Size(min=5, max=20, message = "username size between 5 and 20 characters ")
        String username, @NotNull @NotBlank String password) {
}
