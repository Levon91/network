package com.example.demo.common.util.converter.base;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The dto list base converter implementation.
 *
 * @author <a href="mailto:lstonoyan@gmail.com">Levon Tonoyan</a>
 */
public abstract class DtoListBaseConverter<S, T>
        extends DtoBaseConverter<S, T> implements DtoListConverter<S, T> {
    /**
     * Converts list of sources to list of targets.
     *
     * <b><i>Note: </i></b> It is important to implement the method {@link #createNewTarget()}  when you use this method.
     * */
    public List<T> convert(List<S> sources) {
        List<T> targets = new ArrayList<>();

        for (S source : sources) {
            T target = createNewTarget();
            convert(source, target);
            targets.add(target);
        }

        return targets;
    }

    /**
     * Converts list of sources to list of targets.
     * */
    public void convert(List<S> sources, List<T> targets) {
        if (CollectionUtils.isEmpty(sources)
                || CollectionUtils.isEmpty(targets)
                || sources.size() != targets.size()) {
            throw new IllegalArgumentException();
        }

        Iterator<T> it = targets.iterator();
        for (S source : sources) {
            T target = it.next();
            convert(source, target);
        }
    }

    /**
     * Factory method that creates new target.
     *
     * <b><i>Note: </i></b> It is important to implement this method by the contract of using the method {@link #convert(List)}.
     */
    protected T createNewTarget() {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     *
     * @see DtoConverter#convert(Object, Object)
     */
    @Override
    public abstract void convert(S source, T target);
}
