package nl.bestego.huesim.util;

import java.util.Set;
import java.util.stream.Collectors;

public class Converter {
    public static String set2csv(Set<Long> set) {

        return set.stream()
                .sorted()
                .map(number -> number.toString())
                .collect(Collectors.joining(","));
    }
}
