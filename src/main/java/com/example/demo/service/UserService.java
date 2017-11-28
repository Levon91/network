package com.example.demo.service;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.*;
import com.example.demo.common.dto.ResponseStatus;
import com.example.demo.common.dto.user.UserDto;
import com.example.demo.common.exception.rs.ServerUnavailableException;
import com.example.demo.common.response_dto.UserResponseDto;
import com.example.demo.common.util.converter.UserConverter;
import com.example.demo.common.util.validator.CommonValidator;
import com.example.demo.manager.impl.UserManager;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.example.demo.service.SmsService.sendSms;

/**
 * The user service.
 *
 * @author <a href="mailto:lstonoyan@gmail.com">Levon Tonoyan</a>
 */
@RestController
@RequestMapping(value = "/user")
public class UserService {

    @Autowired
    private UserManager userManager;

    @Autowired
    private UserConverter userConverter;

    @PostMapping
    @RequestMapping("/add")
    public UserResponseDto addUser(@RequestParam("first_name") String firstName,
                                   @RequestParam("last_name") String lastName,
                                   @RequestParam("mobile_number") String mobileNumber) {
        UserResponseDto result = new UserResponseDto();
        User user;

        if (firstName == null || firstName.trim().length() == 0) {
            result.setResponseStatus(ResponseStatus.INVALID_PARAMETER);
            result.setMessage("Invalid first name");
        }

        if (lastName == null || lastName.trim().length() == 0) {
            result.setResponseStatus(ResponseStatus.INVALID_PARAMETER);
            result.setMessage("Invalid last name");
        }

        if (mobileNumber.trim().length() == 0 || !CommonValidator.isValidMobileNumber(mobileNumber)) {
            result.setResponseStatus(ResponseStatus.INVALID_PARAMETER);
            result.setMessage("Invalid mobile number");
        }
        if (result.getResponseStatus() == null) {
            try {
                User toBeSaved = new User(firstName, lastName, mobileNumber);
                user = userManager.addUser(toBeSaved);
                UserDto userDto = userConverter.convert(user);
                String message = "Your verification code is 123456";
                sendSms(message, mobileNumber);
                result.setUser(userDto);
                result.setResponseStatus(ResponseStatus.SUCCESS);
                result.setMessage("User successfully added");
            } catch (ServerUnavailableException e) {
                result.setResponseStatus(ResponseStatus.SERVER_MAINTENANCE);
                result.addMessage("Server unavailable");
            } catch (Exception e) {
                System.out.println(e);
                result.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
                result.addMessage("Internal server error");
            }
        }
        return result;
    }


}
