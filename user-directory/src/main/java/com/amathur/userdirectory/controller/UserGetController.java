package com.amathur.userdirectory.controller;

import com.amathur.userdirectory.address.dto.AddressDTO;
import com.amathur.userdirectory.address.handler.UserAddressHandler;
import com.amathur.userdirectory.dto.UserResponseDTO;
import com.amathur.userdirectory.exception.UserException;
import com.amathur.userdirectory.exception.UserNotFoundException;
import com.amathur.userdirectory.service.UserService;
import com.amathur.userdirectory.user.User;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.amathur.userdirectory.exception.ExceptionHandler.getExceptionResponse;

@RestController
@RequestMapping("/user/find")
public class UserGetController
{
    @Autowired
    UserService service;

    @Autowired
    UserAddressHandler userAddressHandler;

    /**
     * Finds a user, throws a wrapped UserNotFoundException if no user found
     * @param id
     * @return
     * @throws UserNotFoundException
     */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> findUser(@PathVariable(name = "id") int id) throws UserException
    {
        User user = service.findUser(id);
        ResponseEntity<Map<String, Object>> addressResponse = userAddressHandler.fetchAddress(user.getAddressId());
        if(addressResponse.getStatusCode().is2xxSuccessful())
        {
            System.out.println("[Controller] Successfully retrieved Address of ID : " + id + " Values : " + addressResponse.getBody());
            UserResponseDTO userResponse = getUserResponseDTO(user, addressResponse);
            Map<String, Object> response = new HashMap<>();
            response.put("status", 200);
            response.put("data", userResponse);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        else if(addressResponse.getStatusCode().is4xxClientError())
        {
            System.out.println("[Controller] [ERROR] There was some issue while getting Address of ID : " + id);
            throw new UserException("Unable to fetch Address with ID " + id);
        }
        return null;
    }

    private static UserResponseDTO getUserResponseDTO(User user, ResponseEntity<Map<String, Object>> addressResponse)
    {
        AddressDTO address = new AddressDTO();
        Map<String, Object> addressData = addressResponse.getBody();
        address.setCity((String) addressData.get("city"));
        address.setCountry((String) addressData.get("country"));
        address.setPincode((Integer) addressData.get("pincode"));
        UserResponseDTO userResponse= new UserResponseDTO(user, address);
        return userResponse;
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> findAllUsers() throws UserException
    {
        List<User> users = service.findAllUsers();
        List<UserResponseDTO> userResponse = new ArrayList<>();
        for(User user : users)
        {
            int id = user.getAddressId();
            ResponseEntity<Map<String, Object>> addressResponse = userAddressHandler.fetchAddress(id);
            if (addressResponse.getStatusCode().is2xxSuccessful())
            {
                UserResponseDTO userResponseDTO = getUserResponseDTO(user, addressResponse);
                userResponse.add(userResponseDTO);
            }
            else
            {
                System.out.println("[Controller] [ERROR] There was some issue while getting Address of ID : " + id + " for the user with ID : " + user.getId());
                throw new UserException("Unable to fetch Address with ID " + id);
            }
        }
        Map<String, Object> response = new HashMap<>();
        response.put("status", 200);
        response.put("data", userResponse);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @ExceptionHandler(FeignException.class)
    public ResponseEntity handleFeignException(FeignException exception)
    {
        System.out.println("[GET] [Controller] [ERROR] Feign Exception caused probably because no Address match was found for some Address ID");
        String message = exception.getMessage();
        try
        {
            String errorMessage = exception.contentUTF8();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(errorMessage);
            message = jsonNode.get("errors").get("message").asText();
            System.out.println("Feign Exception converting msg : " + message);
        }
        finally
        {
            return getExceptionResponse(new UserException(message));
        }
    }

    /**
     * Exception specific to GET and null checks for this class
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
     * Exception generic, used here in context of handling any issues during fetch
     * @param exception
     * @return
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity handleUserNotFoundException(UserNotFoundException exception)
    {
        return getExceptionResponse(exception);
    }
}
