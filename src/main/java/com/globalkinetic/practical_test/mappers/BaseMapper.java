package com.globalkinetic.practical_test.mappers;

/**
 * @author Joesta
 */
public interface BaseMapper<S, T> {
    T toTarget(S source);
   // S toSource(T target);
}
