package com.amathur.userdirectory.user;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "\"User\"")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String name;
    int age;
    int addressId;
    String email;
}
