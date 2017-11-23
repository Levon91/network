package com.example.demo.common.util.converter.base;

import java.util.ArrayList;
import java.util.List;

/**
 * The base converter implementation.
 *
 * @author <a href="mailto:lstonoyan@gmail.com">Levon Tonoyan</a>
 */
public abstract class BaseConverter<S, T> implements Converter<S, T> {
    /**
     * Converts the sources of type S to result of type T.
     * @param sources the sources
     * @return the converted result of type T
     */
    public List<T> convert(List<S> sources) {
        List<T> targets = new ArrayList<>(sources.size());
        for (S source : sources) {
            targets.add(convert(source));
        }
        return targets;
    }

    /*
     * (non-Javadoc)
     *
     * @see Converter#convert(Object)
     */
    @Override
    public abstract T convert(S source);
}
