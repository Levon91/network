package com.example.demo.common.util;

import com.example.demo.common.util.validator.CommonValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

/**
 * The suite of utilities for general purposes.
 *
 * @author <a href="mailto:lstonoyan@gmail.com">Levon Tonoyan</a>
 */
public final class Utils {
    /**
     * The encoding.
     */
    private static final String ENCODING = "UTF-8";

    /**
     * The decimal digits pattern.
     */
    private static final Pattern DIGIT_PATTERN = Pattern.compile("[0-9]+");
    /**
     * The alphabetic characters pattern.
     */
    private static final Pattern LETTER_PATTERN = Pattern.compile("[a-zA-Z]+");

    /**
     * The hidden symbol (placeholder).
     */
    private static final char HIDDEN_SYMBOL = '*';

    /**
     * The percentage suffix.
     */
    private static final String PERCENTAGE_SUFFIX = "%";

    private static final String START_VIRTUAL_CARD_VAL = "0001";

    /**
     * The default currency code.
     */
    private static final String DEFAULT_CURRENCY_CODE = "AMD";

    private static final int MOBILE_NUMBER_MAX_SIZE = 15;

    private static final int TRANSACTION_AMOUNT_PRECISION = 2;

    public static final BigDecimal BIG_DECIMAL_100 = new BigDecimal(100);

    private Utils() {
    }

    public static boolean isDigit(String str) {
        return !CommonValidator.isEmpty(str) && DIGIT_PATTERN.matcher(str).matches();
    }

    /**
     * Returns {@code true} if the given string contains
     * one or more digits, otherwise returns {@code false}.
     *
     * @param str The string.
     * @return {@code true} if the given string contains
     * one or more digits, otherwise returns {@code false}.
     */
    public static boolean containsDigit(String str) {
        return DIGIT_PATTERN.matcher(str).matches();
    }

    /**
     * Returns {@code true} if the given string contains
     * one or more digits, otherwise returns {@code false}.
     *
     * @param str The string.
     * @return {@code true} if the given string contains
     * one or more digits, otherwise returns {@code false}.
     */
    public static boolean containsLetter(String str) {
        return LETTER_PATTERN.matcher(str).matches();
    }

    /**
     * Returns {@code true} if the password is valid.
     * <p>
     * Password should contain at least seven characters;
     * both numeric and alphabetic characters are required.
     *
     * @param password The password.
     * @return {@code true} if the given password is valid.
     */
    public static boolean isPasswordValid(final String password) {
        if (containsDigit(password) && containsLetter(password)) {
            return true;
        }
        return false;
    }

    /**
     * Returns the instance of {@code ByteArrayInputStream} for the given string.
     *
     * @param str the string
     * @return the instance of {@code ByteArrayInputStream}
     */
    public static ByteArrayInputStream getByteArrayInputStream(String str) {
        try {
            byte[] resultBytes = str.getBytes(ENCODING);
            return new ByteArrayInputStream(resultBytes);
        } catch (UnsupportedEncodingException e) {
        }
        return null;
    }

    /**
     * Hides the {@code beforeCount} and {@code afterCount} count of symbols in the {@code input} string.
     *
     * @param input       the input string
     * @param beforeCount the count of symbols to hide before
     * @param afterCount  the count of symbols to hide after
     * @return the result string which contains the {@code beforeCount} and {@code afterCount} count of hidden symbols
     */
    public static String hideStringSymbols(String input, int beforeCount, int afterCount) {
        return hideStringSymbols(input, beforeCount, afterCount, HIDDEN_SYMBOL);
    }

    /**
     * Hides the {@code beforeCount} and {@code afterCount} count of symbols in the {@code input} string,
     * using the {@code hiddenSymbol}.
     *
     * @param input        the input string
     * @param beforeCount  the count of symbols to hide before
     * @param afterCount   the count of symbols to hide after
     * @param hiddenSymbol the hidden symbol
     * @return the result string which contains the {@code beforeCount} and {@code afterCount} count of hidden symbols
     * of the {@code hiddenSymbol}
     */
    public static String hideStringSymbols(String input, int beforeCount, int afterCount, char hiddenSymbol) {
        int len = input.length();
        if (beforeCount < 0 || afterCount < 0 || beforeCount + afterCount > len) {
            throw new IllegalArgumentException("The before or/and after count argument is wrong");
        }
        StringBuilder result = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            if (i < beforeCount || len - i <= afterCount) {
                result.append(input.charAt(i));
            } else {
                result.append(hiddenSymbol);
            }
        }
        return result.toString();
    }

    public static BigDecimal divide(BigDecimal v1, double v2) {
        return v1.setScale(2, RoundingMode.HALF_UP).divide(new BigDecimal(v2), RoundingMode.HALF_UP);
    }

    public static BigDecimal divide(BigDecimal v1, BigDecimal v2) {
        return v1.setScale(2, RoundingMode.HALF_UP).divide(v2, RoundingMode.HALF_UP);
    }

    public static String cutMobileNumber(String mobileNumber) {
        if (CommonValidator.isEmpty(mobileNumber)) {
            return mobileNumber;
        }

        mobileNumber = cleanMobileNumberNonDigits(mobileNumber);

        if (mobileNumber.length() == 9 && mobileNumber.startsWith("0")) {
            mobileNumber = mobileNumber.substring(1);
        } else if (mobileNumber.length() == 11 && mobileNumber.startsWith("374")) {
            mobileNumber = mobileNumber.substring(3);
        } else if (mobileNumber.startsWith("+374") || mobileNumber.startsWith("0374")) {
            mobileNumber = mobileNumber.substring(4);
        } else if (mobileNumber.startsWith("00374")) {
            mobileNumber = mobileNumber.substring(5);
        } else if (mobileNumber.startsWith("00")) {
            mobileNumber = mobileNumber.substring(2);
        } else if (mobileNumber.startsWith("+") || mobileNumber.startsWith("0")) {
            mobileNumber = mobileNumber.substring(1);
        }

        return mobileNumber;
    }

    public static String sanitizeMobileNumber(String mobileNumber) {
        if (CommonValidator.isEmpty(mobileNumber)) {
            return mobileNumber;
        }

        mobileNumber = cleanMobileNumberNonDigits(mobileNumber);

        if (mobileNumber.length() == 9 && mobileNumber.startsWith("0")) {
            mobileNumber = "00374" + mobileNumber.substring(1);
        } else if (mobileNumber.length() == 8) {
            mobileNumber = "00374" + mobileNumber;
        } else if (!mobileNumber.startsWith("00")) {
            if (mobileNumber.startsWith("+") || mobileNumber.startsWith("0")) {
                mobileNumber = "00" + mobileNumber.substring(1);
            } else {
                mobileNumber = "00" + mobileNumber;
            }
        }

        return mobileNumber;
    }

    public static boolean isValidSanitizeMobileNumber(String mobileNumber) {
        return mobileNumber.length() > MOBILE_NUMBER_MAX_SIZE;
    }

    private static String cleanMobileNumberNonDigits(String mobileNumber) {
        mobileNumber = mobileNumber.trim();

        boolean containsSign = false;
        if (mobileNumber.charAt(0) == '+') {
            mobileNumber = mobileNumber.substring(1);
            containsSign = true;
        }

        mobileNumber = mobileNumber.replaceAll("[^\\d]", "");

        if (containsSign) {
            mobileNumber = '+' + mobileNumber;
        }

        return mobileNumber;
    }

    /**
     * Formats the given amount.
     *
     * @param amount the amount
     * @return the formatted amount string
     */
    public static String formatAmount(BigDecimal amount) {
        return formatAmount(amount, DEFAULT_CURRENCY_CODE);
    }

    /**
     * Formats the given amount.
     *
     * @param amount       the amount
     * @param currencyCode the currency code
     * @return the formatted amount string
     */
    public static String formatAmount(BigDecimal amount, String currencyCode) {
        NumberFormat nf = new DecimalFormat();
        nf.setCurrency(java.util.Currency.getInstance(currencyCode));
        nf.setGroupingUsed(true);
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        return nf.format(amount);
    }

    /**
     * Trims the given string array elements.
     *
     * @param strArray the string array
     */
    public static void trim(String[] strArray) {
        for (int i = 0; i < strArray.length; i++) {
            strArray[i] = strArray[i].trim();
        }
    }

    /**
     * Finds the resource with the given name for the class loader of the given class.
     *
     * @param cls  the class
     * @param name the resource name
     * @return the {@code URL} object for reading the resource
     */
    public static URL getCLResource(Class<?> cls, String name) {
        return cls.getClassLoader().getResource(name);
    }

    /**
     * Finds the resource with the given name for the class loader of the given class as a stream.
     *
     * @param cls  the class
     * @param name the resource name
     * @return the input stream for reading the resource
     */
    public static InputStream getCLResourceAsStream(Class<?> cls, String name) {
        return cls.getClassLoader().getResourceAsStream(name);
    }

    /**
     * Returns the object of the given Java type from JSON string representation.
     *
     * @param jsonString JSON string representation
     * @param cls        the class of Java type
     * @param <T>        the Java type
     * @return the object of the given Java type
     * @throws IOException if the problem encountered when processing (parsing) JSON content
     */
    public static <T> T jsonStringToObject(String jsonString, Class<T> cls) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, cls);
    }

    /**
     * Returns the list of elements of the given Java type from JSON string representation.
     *
     * @param jsonString JSON string representation
     * @param cls        the class of Java type
     * @param <T>        the Java type
     * @return the object of the given Java type
     * @throws IOException if the problem encountered when processing (parsing) JSON content
     */
    public static <T> List<T> jsonStringToList(String jsonString, Class<T> cls) throws IOException {
        return jsonStringToCollection(jsonString, List.class, cls);
    }

    /**
     * Returns the collection of elements of the given Java type from JSON string representation.
     *
     * @param jsonString      JSON string representation
     * @param collectionClass the class of collection
     * @param elementClass    the class of element of collection
     * @param <C>             the type of collection
     * @param <E>             the type of element of collection
     * @return the object of the given Java type
     * @throws IOException if the problem encountered when processing (parsing) JSON content
     */
    public static <C extends Collection, E> C jsonStringToCollection(String jsonString, Class<? extends Collection> collectionClass
            , Class<E> elementClass) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, objectMapper.getTypeFactory().constructCollectionType(collectionClass, elementClass));
    }

    /**
     * Returns JSON string representation of the given object.
     *
     * @param obj the object
     * @return JSON string representation
     * @throws JsonProcessingException if the problem encountered when processing (generating) JSON content
     */
    public static String objectToJsonString(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    /**
     * Scales the transaction amount.
     *
     * @param amount the amount
     * @return the scaled transaction amount
     */
    public static BigDecimal scaleTransactionAmount(BigDecimal amount) {
        if (amount.scale() != TRANSACTION_AMOUNT_PRECISION) {
            amount = amount.setScale(TRANSACTION_AMOUNT_PRECISION, RoundingMode.HALF_UP);
        }
        return amount;
    }
}
