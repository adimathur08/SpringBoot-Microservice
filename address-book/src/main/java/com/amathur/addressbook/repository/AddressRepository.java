package com.amathur.addressbook.repository;

import com.amathur.addressbook.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>
{

}
