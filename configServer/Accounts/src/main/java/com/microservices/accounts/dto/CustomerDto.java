package com.microservices.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Customer",description = "Schema to hold customer and account information")

public class CustomerDto {

    @Schema(name = "name", description = "Customer name")
    @NotEmpty(message = "Name can't be Empty.")
    @Size(min = 5, max = 30, message = "length of name should be between 5 and 30 characters.")
    private String name;

    @NotEmpty(message = "Email can't be Empty.")
    @Email(message = "Email is not valid.")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    private AccountsDto accountsDto;
}
