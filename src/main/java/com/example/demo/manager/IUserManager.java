package com.example.demo.manager;

import com.example.demo.common.exception.rs.ServerUnavailableException;
import com.example.demo.model.User;

import java.util.List;

public interface IUserManager {

    User addUser(User user) throws ServerUnavailableException;

    User findById(long id) throws ServerUnavailableException;

    User findByFirstName(String firstName) throws ServerUnavailableException;

    User findByMobileNumber(String mobileNumber) throws ServerUnavailableException;

    List<User> findAll() throws ServerUnavailableException;

    boolean deleteByMobileNumber(String mobileNumber) throws ServerUnavailableException;
}
