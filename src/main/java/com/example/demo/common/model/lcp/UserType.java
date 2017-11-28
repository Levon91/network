package com.example.demo.common.model.lcp;

/**
 * Enum type for user type.
 *
 * @author <a href="mailto:lstonoyan@gmail.com">Levon Tonoyan</a>
 */
public enum UserType {
    REGULAR(1),
    PREMIUM(2);

    private int value;

    UserType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static UserType valueOf(final int value) {
        for (UserType e : values()) {
            if (e.getValue() == value) return e;
        }
        return null;
    }
}
