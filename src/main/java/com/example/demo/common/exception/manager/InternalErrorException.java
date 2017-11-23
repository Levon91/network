package com.example.demo.common.exception.manager;

/**
 * The internal error exception.
 * Indicates that an internal error has occurred.
 *
 * @author <a href="mailto:lstonoyan@gmail.com">Levon Tonoyan</a>
 */
public final class InternalErrorException extends Exception {

    public InternalErrorException() {
        super();
    }

    public InternalErrorException(String message) {
        super(message);
    }

    public InternalErrorException(Throwable e) {
        super(e);
    }

    public InternalErrorException(String message, Throwable e) {
        super(message, e);
    }

}
