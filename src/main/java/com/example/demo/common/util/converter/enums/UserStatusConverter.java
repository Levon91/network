package com.example.demo.common.util.converter.enums;

import com.example.demo.common.model.lcp.UserStatus;

import javax.persistence.AttributeConverter;

/**
 * The enum converter for UserStatus.
 *
 * @author <a href="mailto:lstonoyan@gmail.com">Levon Tonoyan</a>
 */
public class UserStatusConverter implements AttributeConverter<UserStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(UserStatus attribute) {
        return attribute.getValue();
    }

    @Override
    public UserStatus convertToEntityAttribute(Integer dbData) {
        return UserStatus.valueOf(dbData);
    }
}
