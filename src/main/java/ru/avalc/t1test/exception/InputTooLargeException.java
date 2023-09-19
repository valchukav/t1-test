package ru.avalc.t1test.exception;

/**
 * @author Alexei Valchuk, 19.09.2023, email: a.valchukav@gmail.com
 */

public class InputTooLargeException extends RuntimeException {

    public InputTooLargeException() {
    }

    public InputTooLargeException(String message) {
        super(message);
    }
}
