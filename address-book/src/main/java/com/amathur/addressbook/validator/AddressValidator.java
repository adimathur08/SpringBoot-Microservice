package com.amathur.addressbook.validator;

import com.amathur.addressbook.dto.AddressDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
public class AddressValidator
{
    public void validateAddress(@Valid AddressDTO address)
    {}
}
