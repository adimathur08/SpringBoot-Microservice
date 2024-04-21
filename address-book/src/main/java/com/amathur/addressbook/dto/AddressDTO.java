package com.amathur.addressbook.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddressDTO
{
    @Valid

    @NotBlank(message = "City cannot be empty")
    @NotNull(message = "City cannot be null")
    @Size(min = 2, max = 10, message = "City size should be of length 2-10 chars")
    String city;

    @NotBlank(message = "Country cannot be empty")
    @NotNull(message = "Country cannot be null")
    @Size(min = 2, max = 10, message = "Country size should be of length 2-10 chars")
    String country;

    @NotBlank(message = "Pincode cannot be empty")
    @NotNull(message = "Pincode cannot be null")
    @Size(min = 6, max = 6, message = "Pincode should be of size 6")
    String pincode;
}
