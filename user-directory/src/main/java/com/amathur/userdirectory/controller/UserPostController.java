package com.amathur.userdirectory.controller;

import com.amathur.userdirectory.address.dto.AddressDTO;
import com.amathur.userdirectory.address.handler.UserAddressHandler;
import com.amathur.userdirectory.dto.UserAddressDTO;
import com.amathur.userdirectory.dto.UserDTO;
import com.amathur.userdirectory.service.UserService;
import com.amathur.userdirectory.user.User;
import com.amathur.userdirectory.validator.UserValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserPostController
{
    @Autowired
    UserService service;

    @Autowired
    UserValidator validator;

    @Autowired
    UserAddressHandler handler;

    /**
     * Method to save a user.
     * Uses Validator as somehow direct impl of validator was not working here and was returning 200
     * Automatic Exception Handling is used, using Spring's ExceptionHandler :)
     * @param userAddressDTO
     * @param bindingResult
     * @param request
     * @return
     */
    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> save(@RequestBody UserAddressDTO userAddressDTO, BindingResult bindingResult, HttpServletRequest request)
    {
        System.out.println("[Controller] Validating request to save : " + userAddressDTO.toString());
        validator.validateUser(userAddressDTO);
        AddressDTO addressDTO = userAddressDTO.getAddress();
        ResponseEntity<Map<String, Object>> addressResponse = handler.saveAddress(addressDTO);
        int addressId =(Integer) addressResponse.getBody().get("addressId");
        UserDTO user = new UserDTO(userAddressDTO, addressId);
        User savedUser = service.save(user);
        int id = savedUser.getId();
        System.out.println("[Controller] User Saved successfully with ID : " + id);
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("status", 200);
        responseBody.put("message", "User is saved successfully with ID " + id);
        responseBody.put("userId", id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseBody);
    }


    /**
     * Exception specific to POST and data validation
     * @param exception
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    private ResponseEntity<Map<String, Object>> handleConstraintViolationException(ConstraintViolationException exception)
    {
        System.out.println("[Controller] [ERROR] ConstraintViolationException while validating user");
        Map<String, Object> body = new HashMap<>();
        body.put("status", "400");
        Map<String, String> errors = new HashMap<>();
        exception.getConstraintViolations().forEach(
                violations-> errors.put(violations.getPropertyPath().toString(), violations.getMessage())
        );
        body.put("errors", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex)
    {
        System.out.println("[Controller] [ERROR] MethodArgumentNotValidException while validating user");
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors)
    {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }


}
