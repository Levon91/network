package com.example.demo.common.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * The base response dto.
 *
 * @author <a href="mailto:lstonoyan@gmail.com">Levon Tonoyan</a>
 */
@XmlRootElement
public class ResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The response message
     */
    private String message;

    /**
     * The response status
     */
    private ResponseStatus responseStatus;

    public void addMessage(String message) {
        this.message = this.message == null ? message : this.message + ",\r\n" + message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }
}
