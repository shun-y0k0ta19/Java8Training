package ch08.ex07;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Comparators {
    public static <T extends Comparable<? super T>> Comparator<T> reversedNullsFirst() {
        return Comparator.nullsLast(Collections.reverseOrder());
    }

    public static void main(String[] args) {
        List<String> strings = Arrays.asList(null, "A", "B", "C", null);
        Collections.sort(strings, reversedNullsFirst());
        strings.forEach(System.out::println);
    }
}
