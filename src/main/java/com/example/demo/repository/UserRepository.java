package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    User save(User user);

    Optional<User> findById(long id);

    Optional<User> findByFirstName(String firstName);

    Optional<User> findByMobileNumber(String mobileNumber);

    List<User> findAll();

    boolean deleteByMobileNumber(String mobileNumber);
}
