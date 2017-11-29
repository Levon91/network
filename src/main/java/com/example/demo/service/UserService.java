package com.example.demo.service;

import com.example.demo.common.dto.ResponseDto;
import com.example.demo.common.dto.ResponseStatus;
import com.example.demo.common.dto.user.UserDto;
import com.example.demo.common.exception.rs.ServerUnavailableException;
import com.example.demo.common.model.lcp.UserStatus;
import com.example.demo.common.response_dto.UserResponseDto;
import com.example.demo.common.util.Generator;
import com.example.demo.common.util.Log;
import com.example.demo.common.util.converter.UserConverter;
import com.example.demo.common.util.validator.CommonValidator;
import com.example.demo.manager.impl.UserManager;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.example.demo.service.SmsService.sendSms;

/**
 * The user service.
 *
 * @author <a href="mailto:lstonoyan@gmail.com">Levon Tonoyan</a>
 */
@RestController
@RequestMapping(value = "/user")
public class UserService {

    private UserManager userManager;

    private UserConverter userConverter;

    @Autowired
    public UserService(UserManager userManager, UserConverter userConverter) {
        this.userManager = userManager;
        this.userConverter = userConverter;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public UserResponseDto addUser(@RequestParam("first_name") String firstName,
                                   @RequestParam("last_name") String lastName,
                                   @RequestParam("mobile_number") String mobileNumber) {
        UserResponseDto result = new UserResponseDto();
        User user;

        if (firstName == null || firstName.trim().length() == 0) {
            result.setResponseStatus(ResponseStatus.INVALID_PARAMETER);
            result.setMessage("invalid.first.name");
        }

        if (lastName == null || lastName.trim().length() == 0) {
            result.setResponseStatus(ResponseStatus.INVALID_PARAMETER);
            result.setMessage("invalid.last.name");
        }

        if (mobileNumber.trim().length() == 0 || !CommonValidator.isValidMobileNumber(mobileNumber)) {
            result.setResponseStatus(ResponseStatus.INVALID_PARAMETER);
            result.setMessage("invalid.mobile.number");
        }
        if (result.getResponseStatus() == null) {
            try {
                boolean isMobileNumberExists = userManager.isMobileNumberExists(mobileNumber);
                if (isMobileNumberExists) {
                    result.setResponseStatus(ResponseStatus.CONFLICT_DUPLICATE_DATA);
                    result.setMessage("mobile.number.exists");
                    return result;
                }
                String pinCode = Generator.getDigits(5);
                User toBeSaved = new User(firstName, lastName, mobileNumber, pinCode);
                user = userManager.addUser(toBeSaved);
                UserDto userDto = userConverter.convert(user);

                String message = "Your pin code is: " + pinCode;
                sendSms(message, mobileNumber);

                result.setUser(userDto);
                result.setResponseStatus(ResponseStatus.SUCCESS);
                result.setMessage("user.added");
            } catch (ServerUnavailableException e) {
                Log.inform.info("Server unavailable exception: " + e.getMessage());
                result.setResponseStatus(ResponseStatus.SERVER_MAINTENANCE);
                result.addMessage("server.unavailable");
            } catch (Exception e) {
                Log.inform.info("Internal error: " + e.getMessage());
                result.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
                result.addMessage("internal.server.error" + " add user operation");
            }
        }
        return result;
    }

    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    public ResponseDto verifyUser(@RequestParam("pin_code") String pinCode,
                                  @RequestParam("mobile_number") String mobileNumber) {

        ResponseDto result = new ResponseDto();

        if (pinCode == null || pinCode.trim().length() == 0) {
            result.setResponseStatus(ResponseStatus.INVALID_PARAMETER);
            result.setMessage("invalid.pin.code");
        }
        if (mobileNumber.trim().length() == 0 || !CommonValidator.isValidMobileNumber(mobileNumber)) {
            result.setResponseStatus(ResponseStatus.INVALID_PARAMETER);
            result.setMessage("invalid.mobile.number");
        }
        if (result.getResponseStatus() == null) {
            try {
                User user = userManager.findByMobileNumber(mobileNumber);
                if (user.getPinCode() == null) {
                    result.setMessage("pin.code.not.found");
                    result.setResponseStatus(ResponseStatus.PIN_CODE_NOT_FOUND);
                }
                if (user.getPinCode().equals(pinCode)) {
                    user.setUserStatus(UserStatus.ACTIVE);
                    boolean isUpdated = userManager.updateUser(user);
                    if (isUpdated) {
                        result.setMessage("user.status.updated");
                        result.setResponseStatus(ResponseStatus.SUCCESS);
                    } else {
                        result.setMessage("user.status.update.fail");
                        result.setResponseStatus(ResponseStatus.FORBIDDEN);
                    }
                } else {
                    result.setMessage("pin.code.incorrect");
                    result.setResponseStatus(ResponseStatus.FORBIDDEN);
                }
            } catch (ServerUnavailableException e) {
                Log.inform.info("Server unavailable exception: " + e.getMessage());
                result.setResponseStatus(ResponseStatus.SERVER_MAINTENANCE);
                result.addMessage("server.unavailable");
            } catch (Exception e) {
                Log.inform.info("Internal error: " + e.getMessage());
                result.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
                result.addMessage("internal.server.error" + e.getMessage());
            }
        }
        return result;
    }

    @RequestMapping(value = "/resend_pin", method = RequestMethod.POST)
    public ResponseDto resendPinCode(@RequestParam("mobile_number") String mobileNumber) {
        ResponseDto result = new ResponseDto();

        if (mobileNumber.trim().length() == 0 || !CommonValidator.isValidMobileNumber(mobileNumber)) {
            result.setResponseStatus(ResponseStatus.INVALID_PARAMETER);
            result.setMessage("invalid.mobile.number");
        }

        if (result.getResponseStatus() == null) {
            try {
                User user = userManager.findByMobileNumber(mobileNumber);
                String pinCode = Generator.getDigits(5);
                user.setPinCode(pinCode);
                userManager.updateUserPinCode(user);

                String message = "Your pin code is: " + pinCode;
                sendSms(message, mobileNumber);

                result.setResponseStatus(ResponseStatus.SUCCESS);
                result.setMessage("user.added");
            } catch (ServerUnavailableException e) {
                Log.inform.info("Server unavailable exception: " + e.getMessage());
                result.setResponseStatus(ResponseStatus.SERVER_MAINTENANCE);
                result.addMessage("server.unavailable");
            } catch (Exception e) {
                Log.inform.info("Internal error: " + e.getMessage());
                result.setResponseStatus(ResponseStatus.INTERNAL_ERROR);
                result.addMessage("internal.server.error");
            }
        }
        return result;
    }
}
