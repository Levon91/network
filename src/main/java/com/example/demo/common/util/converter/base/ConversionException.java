package com.example.demo.common.util.converter.base;

/**
 * The conversion exception.
 *
 * @author <a href="mailto:lstonoyan@gmail.com">Levon Tonoyan</a>
 */
public final class ConversionException extends RuntimeException {
    public ConversionException() {
        super();
    }

    public ConversionException(String message) {
        super(message);
    }

    public ConversionException(Throwable e) {
        super(e);
    }

    public ConversionException(String message, Throwable e) {
        super(message, e);
    }
}
