package com.example.demo.common.model.lcp;

public enum UserStatus {
    ACTIVE          (1),
    NOT_ACTIVATED   (2),
    BLOCKED         (3),
    DELETED         (4);

    private int value;

    UserStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static UserStatus valueOf(final int value){
        for (UserStatus e : values()) {
            if (e.getValue() == value) return e;
        }
        return null;
    }
}
