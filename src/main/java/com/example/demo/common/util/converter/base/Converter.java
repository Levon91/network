package com.example.demo.common.util.converter.base;

/**
 * The converter interface.
 *
 * @author <a href="mailto:lstonoyan@gmail.com">Levon Tonoyan</a>
 */
public interface Converter<S, T> {
    /**
     * Converts the source of type S to result of type T.
     * @param source the source
     * @return the converted result of type T
     * @throws ConversionException if something goes wrong during conversion
     */
    T convert(S source) throws ConversionException;
}
