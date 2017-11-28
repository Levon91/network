package com.example.demo.service;

import com.example.demo.common.dto.ResponseDto;
import com.example.demo.common.dto.ResponseStatus;
import com.example.demo.common.dto.user.UserDto;
import com.example.demo.common.exception.rs.ServerUnavailableException;
import com.example.demo.common.model.lcp.UserStatus;
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
                boolean isMobileNumberExists = userManager.isMobileNumberExists(mobileNumber);
                if (isMobileNumberExists) {
                    result.setResponseStatus(ResponseStatus.CONFLICT_DUPLICATE_DATA);
                    result.setMessage("Mobile number already exists");
                    return result;
                }
                User toBeSaved = new User(firstName, lastName, mobileNumber);
                user = userManager.addUser(toBeSaved);
                UserDto userDto = userConverter.convert(user);
//                String message = "Your verification code is 123456";
//                sendSms(message, mobileNumber);
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

    @PostMapping
    @RequestMapping("/verify")
    public ResponseDto verifyUser(@RequestParam("pin_code") String pinCode,
                                  @RequestParam("mobile_number") String mobileNumber) {

        ResponseDto result = new ResponseDto();

        if (pinCode == null || pinCode.trim().length() == 0) {
            result.setResponseStatus(ResponseStatus.INVALID_PARAMETER);
            result.setMessage("Invalid pin code");
        }
        if (mobileNumber.trim().length() == 0 || !CommonValidator.isValidMobileNumber(mobileNumber)) {
            result.setResponseStatus(ResponseStatus.INVALID_PARAMETER);
            result.setMessage("Invalid mobile number");
        }
        if (result.getResponseStatus() == null) {
            try {
                User user = userManager.findByMobileNumber(mobileNumber);
                if (user.getPinCode() == null){
                    result.setMessage("Pin code not found");
                    result.setResponseStatus(ResponseStatus.PIN_CODE_NOT_FOUND);
                }
                if (user.getPinCode().equals(pinCode)) {
                    boolean isUpdated = userManager.updateUserStatus(mobileNumber, UserStatus.ACTIVE);
                    if (isUpdated) {
                        result.setMessage("User status updated to ACTIVE");
                        result.setResponseStatus(ResponseStatus.SUCCESS);
                    } else {
                        result.setMessage("Failed to update user status. try again");
                        result.setResponseStatus(ResponseStatus.FORBIDDEN);
                    }
                } else {
                    result.setMessage("Pin code is incorrect");
                    result.setResponseStatus(ResponseStatus.FORBIDDEN);
                }
            } catch (Exception e) {
                System.out.println(e);
                result.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
                result.addMessage("Internal server error");
            }
        }
        return result;
    }


}
