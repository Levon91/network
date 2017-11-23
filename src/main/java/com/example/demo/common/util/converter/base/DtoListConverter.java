package com.example.demo.common.util.converter.base;

import java.util.List;

/**
 * The dto list converter interface.
 *
 * @author <a href="mailto:lstonoyan@gmail.com">Levon Tonoyan</a>
 */
public interface DtoListConverter<S, T> {
    List<T> convert(List<S> sources) throws ConversionException;

    void convert(List<S> sources, List<T> targets) throws ConversionException;
}
