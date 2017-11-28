package com.example.demo.common.dto;

/**
 * The response status.
 *
 * @author <a href="mailto:lstonoyan@gmail.com">Levon Tonoyan</a>
 */
public enum ResponseStatus {
    SUCCESS                                 (200,   "ok"),
    NO_RESPONSE                             (204,   "no response"),
    BAD_REQUEST                             (400,   "bad request"),
    FORBIDDEN                               (403,   "operation forbidden"),
    RESOURCE_NOT_FOUND                      (404,   "resource not found"),
    CONFLICT_DUPLICATE_DATA                 (409,   "conflict: duplicate data"),
    INTERNAL_ERROR                          (500,   "internal error"),
    NOT_IMPLEMENTED                         (501,   "not implemented"),
    SESSION_EXPIRED                         (100,   "session expired"),
    INVALID_PARAMETER                       (101,   "invalid parameter"),
    NO_DATA_FOUND                           (102,   "no data found"),
    UNAUTHORIZED_REQUEST                    (665,   "unauthorized request"),
    USER_NOT_FOUND                          (666,   "user not found"),
    ORDER_NOT_FOUND                         (667,   "order not found"),
    USER_ACCOUNT_NOT_FOUND                  (668,   "user account not found"),
    UTILITY_SERVICE_NOT_FOUND               (669,   "utility service not found"),
    TRANSACTION_TYPE_NOT_DEFINED            (670,   "transaction type not defined"),
    UNDEFINED_MERCHANT_TYPE                 (671,   "undefined merchant type"),
    NO_ACCOUNT_FOUND                        (672,   "no account found"),
    NO_TARGET_ACCOUNT_FOUND                 (673,   "no target account found"),
    NOT_ENOUGH_AMOUNT                       (674,   "not enough amount"),
    SERVER_MAINTENANCE                      (999,   "The server is under maintenance and not available right now, please try again later"),
    NOT_PERMITTED                           (441,   "Not Permitted"),
    OTP_REQUIRED                            (808,   "One time password required to process transaction"),
    USER_SESSION_EXPIRED                    (685,   "Session expired, try to login"),
    USER_SESSION_NOT_FOUND                  (717,   "Session was not found, please login"),
    OTP_NOT_FOUND                           (711,   "Otp not found"),
    PIN_CODE_NOT_FOUND                      (713,   "Pin code not found"),
    OTP_EXPIRED                             (712,   "Otp Expired"),
    EMAIL_SEND_FAILURE                      (716,   "Failed to send email to support email address/es"),
    EMAIL_NOT_EXISTS                        (718,   "Client's email does not exist"),
    SMS_SEND_FAILED                         (722,   "Failed to send sms "),

    USER_LOCKED                             (731,   "The user is locked"),
    USER_INACTIVATED                        (732,   "The user is inactivated"),
    USER_DELETED                            (733,   "The user is deleted"),

    NEED_APPROVAL                           (734,   "The transaction need approval"),

    USER_NOT_ACTIVATED                      (760,   "The user is not activated yet"),

    MERCHANT_JSON_FORMAT                    (794, "Can not parse JSON"),

    USER_PASSWORDS_NOT_EQUAL                (796, "User passwords are not equal"),
    INCORRECT_USER_PASSWORD_RESET_ID        (797, "Incorrect user password reset id"),

    NOT_SUFFICIENT_FUNDS                    (900, "not enough amount");

    private final int code;
    private final String description;

    ResponseStatus(final int code, final String description) {
        this.code = code;
        this.description = description;
    }

    public static ResponseStatus valueOf(final int code) {

        for (ResponseStatus e : ResponseStatus.values()) {
            if (e.code == code) {
                return e;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns the string representation of this enum constant.
     *
     * @return the string representation of this enum constant.
     */
    @Override
    public String toString() {
        return description + " (" + code + ")";
    }
}
