package com.example.demo.common.exception.rs;

/**
 * The server unavailable exception.
 * Indicates that there is an exception related to the server connection.
 *
 * @author <a href="mailto:lstonoyan@gmail.com">Levon Tonoyan</a>
 */
public final class ServerUnavailableException extends Exception {

    public ServerUnavailableException() {
    }

    public ServerUnavailableException(String message) {
        super(message);
    }

    public ServerUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServerUnavailableException(Throwable cause) {
        super(cause);
    }
}
