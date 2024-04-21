package com.amathur.addressbook.service;

import com.amathur.addressbook.address.Address;
import com.amathur.addressbook.dto.AddressDTO;
import com.amathur.addressbook.exception.AddressNotFoundException;
import com.amathur.addressbook.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService
{
    @Autowired
    AddressRepository repository;

    public Address findById(int id) throws AddressNotFoundException
    {
        System.out.println("[Service] Trying to fetch Address");
        Optional<Address> address = repository.findById(id);
        System.out.println("[Service] Address : " + address.toString());
        if (!address.isPresent())
        {
            System.out.println("[Service] Address for id " + id + " is not present");
            throw new AddressNotFoundException("Unable to find an Address with ID : " + id);
        }
        return address.get();
    }

    public List<Address> findAllAddress()
    {
        return repository.findAll();
    }

    public Address save(AddressDTO addressDTO)
    {
        Address address = new Address();
        address.setCity(addressDTO.getCity());
        address.setCountry(addressDTO.getCountry());
        address.setPincode(Integer.parseInt(addressDTO.getPincode()));
        return repository.save(address);
    }

    public void remove(int id) throws AddressNotFoundException
    {
        repository.delete(findById(id));
    }
}
