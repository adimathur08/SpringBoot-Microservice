package com.amathur.userdirectory.service;

import com.amathur.userdirectory.dto.UserDTO;
import com.amathur.userdirectory.exception.UserNotFoundException;
import com.amathur.userdirectory.repository.UserRepository;
import com.amathur.userdirectory.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService
{
    @Autowired
    UserRepository repository;

    public User save(UserDTO userAddressDTO)
    {
        User user = new User();
        user.setName(userAddressDTO.getName());
        user.setAge(userAddressDTO.getAge());
        user.setAddressId(userAddressDTO.getAddressId());
        user.setEmail(userAddressDTO.getEmail());
        return repository.save(user);
    }

    public List<User> findAllUsers()
    {
        return repository.findAll();
    }

    public User findUser(int id) throws UserNotFoundException
    {
        Optional<User> user = repository.findById(id);
        if(!user.isPresent())
        {
            throw new UserNotFoundException("Unable to find user with ID " + id);
        }
        return user.get();
    }

    public void remove(int id) throws UserNotFoundException
    {
        repository.delete(findUser(id));
    }
}
