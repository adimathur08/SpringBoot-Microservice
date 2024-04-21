package com.amathur.addressbook.controller;

import com.amathur.addressbook.address.Address;
import com.amathur.addressbook.exception.AddressException;
import com.amathur.addressbook.exception.AddressNotFoundException;
import com.amathur.addressbook.service.AddressService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.amathur.addressbook.exception.ExceptionHandler.getExceptionResponse;

@RestController
@RequestMapping("/address/find")
public class AddressGetController
{
    @Autowired
    AddressService service;

    @GetMapping("/{id}")
    public Address findAddress(@PathVariable(name = "id") int id ) throws AddressException
    {
        System.out.println("[Controller] Getting address of ID : " + id);
        Address address = service.findById(id);
        System.out.println("[Controller] Address : " + address.toString());
        return address;
    }

    @GetMapping("/all")
    public List<Address> findAllAddress()
    {
        return service.findAllAddress();
    }


    /**
     * Specific Exception response handler for Address Not Found situations, specific for GET Operation
     * @param exception
     * @return
     */
    @ExceptionHandler(AddressNotFoundException.class)
    private ResponseEntity handleAddressNotFoundException(AddressNotFoundException exception)
    {
        return getExceptionResponse(exception);
    }

    /**
     * Generic Exception response handler for Exceptions wrapped into AddressExceptions, specific for GET Operation
     * This probably will never be used.
     * @param exception
     * @return
     */
    @ExceptionHandler(AddressException.class)
    private ResponseEntity handleAddressException(AddressException exception)
    {
        return getExceptionResponse(exception);
    }
}
