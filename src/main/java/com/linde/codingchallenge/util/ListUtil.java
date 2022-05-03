package com.linde.codingchallenge.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiFunction;

public class ListUtil {

    public static <T, R, U> List<U> zipList(Collection<T> polices, Collection<R> bikes, BiFunction<T, R, U> consumer) {
        Iterator<T> policeIterator = polices.iterator();
        Iterator<R> bikesIterator = bikes.iterator();
        List<U> results = new ArrayList<>();
        while (policeIterator.hasNext() && bikesIterator.hasNext()) {
            results.add(consumer.apply(policeIterator.next(), bikesIterator.next()));
        }
        return results;
    }

}
