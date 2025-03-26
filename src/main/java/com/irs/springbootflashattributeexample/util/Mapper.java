package com.irs.springbootflashattributeexample.util;

import java.util.List;

public interface Mapper<S,T> {

    S toSource(T target);

    T toTarget(S source);

    default List<S> toSource(List<T> targets) {
        if (targets == null) {
            return null;
        }
        return targets.stream().map(target -> toSource(target)).toList();
    }

    default  List<T> toTarget(List<S> sources) {
        if (sources == null) {
            return null;
        }
        return sources.stream().map(source -> toTarget(source)).toList();
    }
}
