package com.amathur.addressbook.controller;

import com.amathur.addressbook.address.Address;
import com.amathur.addressbook.dto.AddressDTO;
import com.amathur.addressbook.service.AddressService;
import com.amathur.addressbook.validator.AddressValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/address")
@Validated
public class AddressPostController
{
    @Autowired
    AddressService service;

    @Autowired
    AddressValidator validator;

    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> saveAddress(@RequestBody @Valid AddressDTO address, BindingResult result, HttpServletRequest request)
    {
        validator.validateAddress(address);
        Address savedAddress = service.save(address);
        int id = savedAddress.getId();
        System.out.println("[Controller] Address Saved successfully with ID : " + id);
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("status", 200);
        responseBody.put("addressId", id);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }


    /**
     * Method specific to handle response for POST request validations
     * @param exception
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    private ResponseEntity<Map<String, Object>> handleAddressException(ConstraintViolationException exception)
    {
        System.out.println("[Controller] [ERROR] ConstraintViolationException, Address is invalid");
        Map<String, Object> body = new HashMap<>();
        body.put("status", "400");
        Map<String, String> errors = new HashMap<>();
        exception.getConstraintViolations().forEach(
                violations-> errors.put(violations.getPropertyPath().toString(), violations.getMessage())
        );
        body.put("errors", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

}
