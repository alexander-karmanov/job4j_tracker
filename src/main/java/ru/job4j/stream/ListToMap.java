package ru.job4j.stream;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ListToMap {
    public static Map<String, Student> convert(List<Student> list) {
        return list.stream().collect(
                Collectors.toMap(
                        s -> s.getSurname(),
                        s -> new Student(s.getScore(), s.getSurname()),
                        (existing, replacement) -> existing
                )
        );
    }
}
