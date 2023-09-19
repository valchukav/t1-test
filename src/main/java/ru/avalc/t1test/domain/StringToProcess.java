package ru.avalc.t1test.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Alexei Valchuk, 19.09.2023, email: a.valchukav@gmail.com
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StringToProcess {

    public static final int MAX_INPUT_SIZE = 100_000_000;
    public static final String MESSAGE_WHEN_NULL = "Input string must not be null";
    public static final String MESSAGE_WHEN_INVALID_SIZE = "Input string size must be greater than 1 and less then 100 000 000 characters";

    @Size(min = 1, max = MAX_INPUT_SIZE, message = MESSAGE_WHEN_INVALID_SIZE)
    @NotNull(message = MESSAGE_WHEN_NULL)
    private String input;
}
