package com.amathur.addressbook.controller;

import com.amathur.addressbook.exception.AddressException;
import com.amathur.addressbook.exception.AddressNotFoundException;
import com.amathur.addressbook.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.amathur.addressbook.exception.ExceptionHandler.getExceptionResponse;

@RestController
@RequestMapping("/address/remove")
public class AddressDeleteController
{
    @Autowired
    AddressService service;

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> removeAddress(@PathVariable(name = "id") int id) throws AddressException
    {
        service.remove(id);
        System.out.println("[Controller] Deleted Address successfully");
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("status", 200);
        responseBody.put("message", "Successfully deleted Address with ID " + id);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }


    /**
     * Specific Exception response handler for Address Not Found situations, specific for DELETE Operation
     * @param exception
     * @return
     */
    @ExceptionHandler(AddressNotFoundException.class)
    private ResponseEntity handleAddressNotFoundException(AddressNotFoundException exception)
    {
        return getExceptionResponse(exception);
    }

    /**
     * Generic Exception response handler for Exceptions wrapped into AddressExceptions, specific for DELETE Operation
     * This is probably never used
     * @param exception
     * @return
     */
    @ExceptionHandler(AddressException.class)
    private ResponseEntity handleAddressException(AddressException exception)
    {
        return getExceptionResponse(exception);
    }
}
