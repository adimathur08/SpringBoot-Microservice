package com.amathur.userdirectory.dto;

import com.amathur.userdirectory.address.dto.AddressDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserAddressDTO
{
    @Valid

    @NotBlank(message = "Name cannot be blank.")
    @NotNull(message = "Name cannot be Null")
    @Size(min = 4, max = 20, message = "Name cannot be less than 4 and more than 20 chars")
    String name;

    @Min(value = 1, message = "Age cannot be less than 1")
    @Max(value = 60, message = "Age cannot be more than 60")
    int age;

    @NotNull(message = "Address cannot be null.")
    AddressDTO address;

    @NotBlank(message = "Email cannot be blank.")
    @NotNull(message = "Email cannot be null.")
    @Email(message = "Invalid Email")
    String email;

}
