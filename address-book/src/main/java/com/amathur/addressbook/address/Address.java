package com.amathur.addressbook.address;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "\"Address\"")
public class Address
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String city;
    String country;
    int pincode;
}
