package com.amathur.userdirectory.dto;

import lombok.Data;

@Data
public class UserDTO
{
    String name;
    int age;
    int addressId;
    String email;

    public UserDTO(UserAddressDTO userAddressDTO, int addressId)
    {
        this.name = userAddressDTO.getName();
        this.age = userAddressDTO.getAge();
        this.addressId = addressId;
        this.email = userAddressDTO.getEmail();
    }
}
