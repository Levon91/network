package com.example.demo.common.util.validator;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The common validator utility.
 *
 * @author <a href="mailto:lstonoyan@gmail.com">Levon Tonoyan</a>
 */
public class CommonValidator {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    private static final DateFormat DATE_TIME_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    //    private static final String REGEX_EMAIL_ADDRESS = "((([a-z]|[A-Z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+(\\.([a-z]|[A-Z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+)*)|((\\x22)((((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(([\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x7f]|\\x21|[\\x23-\\x5b]|[\\x5d-\\x7e]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(\\\\([\\x01-\\x09\\x0b\\x0c\\x0d-\\x7f]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF]))))*(((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(\\x22)))@((([a-z]|[A-Z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|[A-Z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|[A-Z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|[A-Z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-z]|[A-Z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|[A-Z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|[A-Z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|[A-Z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.?";
    private static final String REGEX_EMAIL_ADDRESS = "^([\\p{L}\\.\\_\\-\\d]+)[^~`!#$%^&*()_ <>;:'\"]@([\\p{L}\\-\\_\\.\\d]+)((\\.(\\p{L}){2,63})+)$";

    private static final Pattern REGEX_AMOUNT_SUM = Pattern.compile("[0-9]{1,15}([\\.][0-9]{1,2}){0,1}");

    public static final String REGEX_MOBILE_NUMBER_INTL =
            "^((\\(([\\+]|00|0)?[1-9]+\\))|(([\\+]|00|0)?[1-9]+))[\\.\\-\\s]*" +// country code (required, sign or
                    // double zero or zero is optional)
                    "((\\([0-9]+\\))|[0-9]+)[\\.\\-\\s]*" +// mobile operator code (required) - changed from 1-9 to 0-9 for Humo version
                    "[0-9]{2,4}([\\.\\-\\s]*[0-9]{2,4}){1,2}$";// mobile operator number (required)
    public static final String REGEX_MOBILE_NUMBER_LOCAL =
            "^((([\\+]|00)?374[\\.\\-\\s]*([1-9]{2}|[1-9]0))|(0?([1-9]{2}|[1-9]0)))[\\.\\-\\s]*[0-9]{6}$";// mobile operator number of 6 digits (required)
    public static final String REGEX_MOBILE_NUMBER = REGEX_MOBILE_NUMBER_LOCAL + "|" + REGEX_MOBILE_NUMBER_INTL;
//    public static final String REGEX_MOBILE_NUMBER = "((((00)|(\\+))(374))|(0)?)[1-9]\\d{7}"; //Armenian

    private static final Pattern REGEX_MOBILE_NUMBER_PATTERN = Pattern.compile(REGEX_MOBILE_NUMBER);

    // expression for 'Contract ID' validation
    private static final String REGEX_CONTRACT_ID = "[0-9a-zA-Z]{1,}(([\\-]||[\\_]){0,1}[0-9a-zA-Z]){1,}";

    private static final Pattern REGEX_DIGIT = Pattern.compile("[0-9]+");

    private static final DecimalFormat AMOUNT_FORMAT = new DecimalFormat("00.####");

    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-z])(?=.*[@#!$%^&+*=])(?=[\\u0000-\\u007F]+$)(?=\\S+$).{8,15}$";

    private CommonValidator() {
    }

    public static boolean isEmpty(String str) {
        return StringUtils.isEmpty(str);
    }

    public static boolean isEmpty(String[] array) {
        return ArrayUtils.isEmpty(array);
    }

    public static boolean isEmpty(Collection<?> collection) {
        return CollectionUtils.isEmpty(collection);
    }

    public static boolean isValidEmail(String email) {

        if (isEmpty(email)) {
            return false;
        }
        Pattern pattern = Pattern.compile(REGEX_EMAIL_ADDRESS);
        return pattern.matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {

        if (isEmpty(password)) {
            return false;
        }

        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean isValidMobileNumber(String mobileNumber) {
        return !isEmpty(mobileNumber) && REGEX_MOBILE_NUMBER_PATTERN.matcher(mobileNumber).matches();
    }

    public static boolean isValidDate(String strDate) {
        try {
            DATE_FORMAT.parse(strDate);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks if the given date is before now
     *
     * @param strDate date in string format
     * @return true if given date is after now, false otherwise
     */
    public static boolean isDateBeforeNow(String strDate) {
        try {
            Date date = DATE_TIME_FORMAT.parse(strDate);
            return date.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isDateBeforeNow(long dateTime) {
        return dateTime < System.currentTimeMillis();
    }

    public static boolean isValidAmountOfSum(double sum) {
        return REGEX_AMOUNT_SUM.matcher(AMOUNT_FORMAT.format(sum)).matches();
    }

    /**
     * Checks whether the given string is number or not
     *
     * @param number number in string format
     * @return true if number, false otherwise
     */
    public static boolean isNumeric(String number) {
        boolean stringIsNumeric = true;

        try {
            Double.parseDouble(number);
        } catch (NumberFormatException ex) {
            stringIsNumeric = false;
        }

        return stringIsNumeric;
    }

    /**
     * Checks whether 'Contract ID' is valid or not
     *
     * @param contractID
     * @return <code>java.lang.Boolean</code>
     */
    public static boolean isValidContractID(String contractID) {
        if (isEmpty(contractID)) {
            return false;
        }
        Pattern pattern = Pattern.compile(REGEX_CONTRACT_ID);
        return pattern.matcher(contractID).matches();
    }

    public static boolean isValidBankCode(String code) {
        return true;
    }

    public static boolean isDigit(String str) {
        return !isEmpty(str) && REGEX_DIGIT.matcher(str).matches();
    }

}
