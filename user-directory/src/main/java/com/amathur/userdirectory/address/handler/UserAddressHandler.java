package com.amathur.userdirectory.address.handler;

import com.amathur.userdirectory.address.dto.AddressDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "address-microservice", url = "http://address-book-service:8081/address")
public interface UserAddressHandler
{
    @GetMapping("/find/{id}")
    public ResponseEntity<Map<String, Object>> fetchAddress(@PathVariable(name = "id") int id);

    @PostMapping("save")
    public ResponseEntity<Map<String, Object>> saveAddress(@RequestBody @Valid AddressDTO address);

    @DeleteMapping("remove/{id}")
    public ResponseEntity<Map<String, Object>> removeAddress(@PathVariable(name = "id") int id);

}
