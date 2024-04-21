package com.amathur.userdirectory.validator;

import com.amathur.userdirectory.dto.UserAddressDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
public class UserValidator
{
    public void validateUser(@Valid UserAddressDTO user)
    {
    }
}
