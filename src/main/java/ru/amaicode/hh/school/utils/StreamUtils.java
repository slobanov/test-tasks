package ru.amaicode.hh.school.utils;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.IntStream.range;

public enum StreamUtils {
    ;

    public static <T> Stream<T> flatten2d(T[][] arr) {
        return Stream.of(arr).flatMap(Stream::of);
    }

    public static <T> boolean exists(Function<T, Boolean> p, List<T> list) {
        return list.stream().anyMatch(p::apply);
    }

    public static int[] mapToInt(char[] chars) {
        int l = chars.length;
        return range(0, l).
                map(i -> Character.getNumericValue(chars[i])).
                toArray();
    }
}
