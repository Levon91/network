package com.example.demo.common.util.converter;

import com.example.demo.common.dto.user.UserDto;
import com.example.demo.common.util.converter.base.BaseConverter;
import com.example.demo.model.User;

/**
 * The User base converter.
 *
 * @author <a href="mailto:lstonoyan@gmail.com">Levon Tonoyan</a>
 */
public abstract class UserBaseConverter<S extends User, T extends UserDto> extends BaseConverter<S, T> {
    /**
     * Converts the source of type S to result of type T.
     *  @param source the source
     * @param target the converted result of type T
     */
    protected void convertInternal(S source, T target) {
        target.setId(source.getId());
        target.setFirstName(source.getFirstName());
        target.setLastName(source.getLastName());
        target.setMobileNumber(source.getMobileNumber());
        target.setUserType(source.getUserType());
        target.setUserStatus(source.getUserStatus());
    }
}
