package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    User save(User user);

    Optional<User> findById(long id);

    Optional<User> findByFirstName(String firstName);

    Optional<User> findByMobileNumber(String mobileNumber);

    List<User> findAll();

//    boolean updateUser(User user);

    User deleteUserByMobileNumber(String mobileNumber);

    boolean existsByMobileNumber(String mobileNumber);
}
