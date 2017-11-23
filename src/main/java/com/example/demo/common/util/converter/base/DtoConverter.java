package com.example.demo.common.util.converter.base;

/**
 * The dto converter interface.
 *
 * @author <a href="mailto:lstonoyan@gmail.com">Levon Tonoyan</a>
 */
public interface DtoConverter<S, T> {
    /**
     * Converts the source of type S to result of type T.
     * @param source the source
     * @param target the converted result of type T
     * @throws ConversionException if something goes wrong during conversion
     */
    void convert(S source, T target) throws ConversionException;
}
