package ru.avalc.t1test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.avalc.t1test.domain.Result;
import ru.avalc.t1test.exception.InputTooLargeException;
import ru.avalc.t1test.service.StringService;

import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.avalc.t1test.domain.StringToProcess.MAX_INPUT_SIZE;

/**
 * @author Alexei Valchuk, 19.09.2023, email: a.valchukav@gmail.com
 */

public class StringServiceTests {

    private static StringService stringService;

    @BeforeAll
    public static void init() {
        stringService = new StringService();
    }

    @Test
    public void testWhenStringIsNull() {
        Result result = stringService.processString(null);

        assertThat(result.getOutput()).isEmpty();
    }

    @Test
    public void testWhenStringIsEmpty() {
        Result result = stringService.processString("");

        assertThat(result.getOutput()).isEmpty();
    }

    @Test
    public void testWhenStringIsTooLarge() {
        Assertions.assertThrows(InputTooLargeException.class, () -> stringService.processString("1".repeat(MAX_INPUT_SIZE + 1)));
    }

    @ParameterizedTest
    @MethodSource("ru.avalc.t1test.StringServiceTests#argsForTestDiffOutputs")
    public void testDiffOutputs(String input, Map<Character, Integer> output) {
        Result result = stringService.processString(input);

        assertThat(result.getOutput()).isEqualTo(output);
    }

    private static Stream<Arguments> argsForTestDiffOutputs() {
        return Stream.of(
                Arguments.of(" ", Map.of(' ', 1)),
                Arguments.of("   ", Map.of(' ', 3)),
                Arguments.of("88005553535", Map.of('5', 5, '3', 2, '8', 2, '0', 2)),
                Arguments.of("1".repeat(MAX_INPUT_SIZE), Map.of('1', MAX_INPUT_SIZE)),
                Arguments.of("aaaaabcccc", Map.of('a', 5, 'c', 4, 'b', 1))
        );
    }
}
