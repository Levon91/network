package com.example.demo.common.util.converter.enums;

import com.example.demo.common.model.lcp.UserType;

import javax.persistence.AttributeConverter;

public class UserTypeConverter implements AttributeConverter<UserType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(UserType attribute) {
        return attribute.getValue();
    }

    @Override
    public UserType convertToEntityAttribute(Integer dbData) {
        return UserType.valueOf(dbData);
    }
}
