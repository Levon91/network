package com.example.demo.common.util.converter;

import com.example.demo.common.dto.user.UserDto;
import com.example.demo.model.User;
import org.springframework.stereotype.Component;

/**
 * The user converter.
 *
 * @author <a href="mailto:lstonoyan@gmail.com">Levon Tonoyan</a>
 */
@Component
public class UserConverter extends UserBaseConverter<User, UserDto> {

    @Override
    public UserDto convert(User source) {
        UserDto target = new UserDto();
        convertInternal(source, target);
        return target;
    }
}
