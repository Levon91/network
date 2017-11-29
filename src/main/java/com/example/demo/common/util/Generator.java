package com.example.demo.common.util;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The common generator utility.
 *
 * @author <a href="mailto:lstonoyan@gmail.com">Levon Tonoyan</a>
 */
public final class Generator {
    /**
     * Enum type representation of generation types.
     */
    public enum Type {
        DIGITS                  ("0123456789"),
        HEX_DIGITS              ("0123456789abcdef"),
        ALPHABETIC_LOWER        ("abcdefghijklmnopqrstuvwxyz"),
        ALPHABETIC_UPPER        ("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
        ALPHABETIC              ("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"),
        ALPHABETIC_AND_DIGITS   ("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"),
        ALPHABETIC_AND_DIGITS_PASSWORD   ("23456789abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ");

        /**
         * The value of type.
         */
        private final String value;

        /**
         * The regular constructor.
         *
         * @param value The value of type.
         */
        Type(final String value) {
            this.value = value;
        }

        /**
         * Returns the value of type.
         *
         * @return The value of type.
         */
        public String getValue() {
            return value;
        }
    }

    /**
     * Generates the string.
     *
     * @param count the count
     * @param type the type
     *
     * @return the generated string
     */
    public static String get(int count, Type type) {
        Random rand = ThreadLocalRandom.current();

        if (type == null) {
            throw new IllegalArgumentException("Type for 'Value Generator' is NULL");
        }
        if (count <= 0) {
            return null;
        }

        StringBuilder buff = new StringBuilder();
        for (int i = 0; i < count; i++) {
            buff.append(type.value.charAt(rand.nextInt(type.value.length())));
        }
        return buff.toString();
    }

    public static String getDigits(int count) {
        return get(count, Type.DIGITS);
    }

    public static String getHexDigits(int count) {
        return get(count, Type.HEX_DIGITS);
    }

    public static String getAlphabeticLower(int count) {
        return get(count, Type.ALPHABETIC_LOWER);
    }

    public static String getAlphabeticUpper(int count) {
        return get(count, Type.ALPHABETIC_UPPER);
    }

    public static String getAlphabetic(int count) {
        return get(count, Type.ALPHABETIC);
    }

    public static String getAlphabeticAndDigits(int count) {
        return get(count, Type.ALPHABETIC_AND_DIGITS);
    }

    public static String getAlphabeticAndDigitsPassword(int count) {
        return get(count, Type.ALPHABETIC_AND_DIGITS_PASSWORD);
    }


    /**
     * Generates the bytes.
     *
     * @param count the count
     * @param type the type
     *
     * @return the generated string bytes
     */
    public static byte[] getBytes(int count, Type type) {
        Random rand = ThreadLocalRandom.current();

        if (type == null) {
            throw new IllegalArgumentException("Type for 'Value Generator' is NULL");
        }
        if (count <= 0) {
            return null;
        }
        if(type == Type.HEX_DIGITS) {
            return getBytesHex(count);
        }

        int typeLen = type.value.length();
        String typeVal = type.getValue();
        byte [] buff = new byte[count];
        for (int i = 0; i < count; i++) {
            buff[i] = (byte)typeVal.charAt(rand.nextInt(typeLen));
        }
        return buff;
    }


    /**
     * Generates the (hex) bytes.
     *
     * @param count the count
     *
     * @return the generated (hex) bytes
     */
    private static byte[] getBytesHex(int count) {
        Random rand = ThreadLocalRandom.current();

        byte [] buff = new byte[count];
        for (int i = 0; i < count; i++) {
            buff[i] = (byte)(rand.nextInt(16) * 16 + rand.nextInt(16));
        }
        return buff;
    }
}
