package ru.avalc.t1test.service;

import org.springframework.stereotype.Service;
import ru.avalc.t1test.domain.Result;
import ru.avalc.t1test.exception.InputTooLargeException;

import static ru.avalc.t1test.domain.StringToProcess.MAX_INPUT_SIZE;
import static ru.avalc.t1test.domain.StringToProcess.MESSAGE_WHEN_INVALID_SIZE;

/**
 * @author Alexei Valchuk, 19.09.2023, email: a.valchukav@gmail.com
 */

@Service
public class StringService {

    public Result processString(String stringToProcess) {
        Result result = new Result();

        if (stringToProcess != null) {
            if (stringToProcess.length() > MAX_INPUT_SIZE) {
                throw new InputTooLargeException(MESSAGE_WHEN_INVALID_SIZE);
            }

            if (!stringToProcess.isEmpty()) {
                for (int i = 0; i < stringToProcess.length(); i++) {
                    result.insertIntoOutput(stringToProcess.charAt(i));
                }
                result.sort();
            }
        }

        return result;
    }
}
