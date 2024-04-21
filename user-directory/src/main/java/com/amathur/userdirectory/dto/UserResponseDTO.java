package com.amathur.userdirectory.dto;

import com.amathur.userdirectory.address.dto.AddressDTO;
import com.amathur.userdirectory.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO
{
    String name;
    int age;
    AddressDTO address;
    String email;

    public UserResponseDTO(User user, AddressDTO address)
    {
        System.out.println("[UserResponseDTO] Trying to add User and Address to UserResponse");
        this.address = address;
        this.name = user.getName();
        this.age = user.getAge();
        this.email = user.getEmail();
    }
}
