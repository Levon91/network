package com.example.demo.manager.impl;

import com.example.demo.common.exception.rs.ServerUnavailableException;
import com.example.demo.manager.IUserManager;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * The user manager implementation.
 *
 * @author <a href="mailto:lstonoyan@gmail.com">Levon Tonoyan</a>
 */
@Component
public class UserManager implements IUserManager {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) throws ServerUnavailableException {
        return userRepository.save(user);
    }

    @Override
    public User findById(long id) throws ServerUnavailableException {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public User findByFirstName(String firstName) throws ServerUnavailableException {
        Optional<User> user = userRepository.findByFirstName(firstName);
        return user.orElse(null);
    }

    @Override
    public User findByMobileNumber(String mobileNumber) throws ServerUnavailableException {
        Optional<User> user = userRepository.findByMobileNumber(mobileNumber);
        return user.orElse(null);
    }

    @Override
    public List<User> findAll() throws ServerUnavailableException {
        return userRepository.findAll();
    }

    @Override
    public boolean deleteByMobileNumber(String mobileNumber) throws ServerUnavailableException {
        return userRepository.deleteByMobileNumber(mobileNumber);
    }
}
