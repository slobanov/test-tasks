package ru.amaicode.hh.school.utils;

import java.util.Arrays;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;

public enum PrintUtils {
    ;
    public static String deepToString(Object[][] twoDArray) {
        return Arrays.stream(twoDArray).
                map(arr -> arrToString(arr, 5)).
                collect(joining(System.lineSeparator()));
    }

    private static String arrToString(Object[] arr, int pad) {
        return Arrays.stream(arr).
                map(i -> String.format("%"+pad+"s", i)).
                collect(joining("|"));
    }

    public static String printlns(IntStream ints) {
        return ints.
                mapToObj(Integer::toString).
                collect(joining(System.lineSeparator()));
    }

    public static String printlns(int ...ints) {
        return printlns(Arrays.stream(ints));
    }
}
