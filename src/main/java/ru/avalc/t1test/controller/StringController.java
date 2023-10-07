package ru.avalc.t1test.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.avalc.t1test.domain.Result;
import ru.avalc.t1test.domain.StringToProcess;
import ru.avalc.t1test.service.StringService;

/**
 * @author Alexei Valchuk, 19.09.2023, email: a.valchukav@gmail.com
 */

@RestController
@RequestMapping("/t1")
@AllArgsConstructor
public class StringController {

    private final StringService service;

    @Operation(summary = "Запрос для вычисления частоты встречи символов по заданной строке")
    @PostMapping("/")
    public ResponseEntity<Result> sumCharacters(@Validated @RequestBody StringToProcess stringToProcess) {
        return new ResponseEntity<>(service.processString(stringToProcess.getInput()), HttpStatus.OK);
    }
}
