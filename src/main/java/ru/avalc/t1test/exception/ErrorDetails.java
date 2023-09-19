package ru.avalc.t1test.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Alexei Valchuk, 19.09.2023, email: a.valchukav@gmail.com
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ErrorDetails {

    private LocalDateTime timestamp;
    private String message;
    private String details;
}
