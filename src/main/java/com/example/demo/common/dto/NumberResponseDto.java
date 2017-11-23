package com.example.demo.common.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The number response dto.
 *
 * @author <a href="mailto:lstonoyan@gmail.com">Levon Tonoyan</a>
 */
@XmlRootElement
public class NumberResponseDto extends ResponseDto {
    private static final long serialVersionUID = 1L;

    private long value;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
