package com.example.demo.common.response_dto;

import com.example.demo.common.dto.ResponseDto;
import com.example.demo.common.dto.user.UserDto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The user response dto.
 *
 * @author <a href="mailto:lstonoyan@gmail.com">Levon Tonoyan</a>
 */
@XmlRootElement
public class UserResponseDto extends ResponseDto {
    private static final long serialVersionUID = 1L;

    private UserDto user;

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
