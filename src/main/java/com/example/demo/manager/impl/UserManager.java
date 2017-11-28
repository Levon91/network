package com.example.demo.manager.impl;

import com.example.demo.common.exception.rs.ServerUnavailableException;
import com.example.demo.common.model.lcp.UserStatus;
import com.example.demo.manager.IUserManager;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
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
        try {
            Optional<User> user = userRepository.findById(id);
            return user.orElse(null);
        } catch (EntityNotFoundException e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public User findByFirstName(String firstName) throws ServerUnavailableException {
        try {
            Optional<User> user = userRepository.findByFirstName(firstName);
            return user.orElse(null);
        } catch (EntityNotFoundException e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public User findByMobileNumber(String mobileNumber) throws ServerUnavailableException {
        try {
            Optional<User> user = userRepository.findByMobileNumber(mobileNumber);
            return user.orElse(null);
        } catch (EntityNotFoundException e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public List<User> findAll() throws ServerUnavailableException {
        try {
            return userRepository.findAll();
        } catch (EntityNotFoundException e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public boolean updateUserStatus(String mobileNumber, UserStatus userStatus) throws ServerUnavailableException {
        boolean result = false;
        try {
            result = userRepository.updateUserStatus(mobileNumber, userStatus);
        }catch (EntityNotFoundException e){
            System.out.println(e);
        }
        return result;
    }

    @Override
    public boolean deleteByMobileNumber(String mobileNumber) throws ServerUnavailableException {
        try {
            return userRepository.deleteByMobileNumber(mobileNumber);
        } catch (EntityNotFoundException e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean isMobileNumberExists(String mobileNumber) throws ServerUnavailableException {
        boolean isExists = false;
        try {
            isExists = userRepository.existsByMobileNumber(mobileNumber);
        } catch (EntityNotFoundException e) {
            System.out.println(e);
        }
        return isExists;
    }
}
