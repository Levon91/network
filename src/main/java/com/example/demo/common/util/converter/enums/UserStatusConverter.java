package com.example.demo.common.util.converter.enums;

import com.example.demo.common.model.lcp.UserStatus;
import com.example.demo.common.model.lcp.UserType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class UserStatusConverter implements AttributeConverter<UserStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(UserStatus attribute) {
        return attribute.getValue();
    }

    @Override
    public UserStatus convertToEntityAttribute(Integer dbData) {
        return UserStatus.fromVal(dbData);
    }
}
