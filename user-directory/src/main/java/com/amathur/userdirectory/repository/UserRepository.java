package com.amathur.userdirectory.repository;

import com.amathur.userdirectory.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{

}
