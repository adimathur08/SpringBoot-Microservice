package com.amathur.userdirectory.controller;

import com.amathur.userdirectory.address.handler.UserAddressHandler;
import com.amathur.userdirectory.exception.UserException;
import com.amathur.userdirectory.exception.UserNotFoundException;
import com.amathur.userdirectory.service.UserService;
import com.amathur.userdirectory.user.User;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.amathur.userdirectory.exception.ExceptionHandler.getExceptionResponse;

@RestController
@RequestMapping("/user/remove")
public class UserDeleteController
{
    @Autowired
    UserService service;

    @Autowired
    UserAddressHandler handler;

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable(name = "id")int id, HttpServletResponse response) throws UserException
    {
        User user = service.findUser(id);
        if(user!=null)
        {
            int addressId = user.getAddressId();
            ResponseEntity<Map<String, Object>> responseEntity = handler.removeAddress(addressId);
            if(responseEntity.getStatusCode().is2xxSuccessful())
            {
                service.remove(id);
                System.out.println("[Controller] Deleted User successfully");
                Map<String, Object> responseBody = new HashMap<>();
                responseBody.put("status", 200);
                responseBody.put("message", "Successfully deleted User with ID " + id);
                return ResponseEntity.status(HttpStatus.OK).body(responseBody);
            }
            if (responseEntity.getStatusCode().is4xxClientError())
                throw new UserException("Unable to delete Address linked with User having ID " + id);
        }
        return null;
    }

    /**
     * Exception is specific to DELETE and null checks for this class
     * THIS IS PROBABLY NEVER USED IN OUR CASE
     * @param exception
     * @return
     */
    @ExceptionHandler(UserException.class)
    public ResponseEntity handleUserException(UserException exception)
    {
        return getExceptionResponse(exception);
    }

    /**
     * Exception generic, used here in context of handling any issues during delete
     * @param exception
     * @return
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity handleUserNotFoundException(UserNotFoundException exception)
    {
        return getExceptionResponse(exception);
    }
}
