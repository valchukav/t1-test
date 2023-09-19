package ru.avalc.t1test.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Alexei Valchuk, 19.09.2023, email: a.valchukav@gmail.com
 */

@Getter
@Setter
public class Result {

    private Map<Character, Integer> output;

    public Result() {
        this.output = new HashMap<>();
    }

    public void insertIntoOutput(Character character) {
        output.compute(character, (key, value) -> value == null ? 1 : value + 1);
    }

    public void sort() {
        this.output = this.output.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Character, Integer> pairs : output.entrySet()) {
            stringBuilder.append("\"").append(pairs.getKey()).append("\": ").append(pairs.getValue()).append(", ");
        }
        return stringBuilder.toString().replaceFirst(", $", "");
    }
}
