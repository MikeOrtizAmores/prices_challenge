package com.ortizmiguelangel.exception;

import com.ortizmiguelangel.exception.code.ExceptionCode;
import com.ortizmiguelangel.generatedapi.query.model.ErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerController {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handleGenericException(RuntimeException ex) {
        log.error("Generic exception thrown - {}", ex.getMessage());
        return new ErrorDTO(ExceptionCode.EXCEPTION_CODE_GENERAL, ex.getMessage());
    }

    @ExceptionHandler(GeneralChallengeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handleGeneralChallengeException(GeneralChallengeException ex) {
        log.error("Generic exception thrown - {}", ex.getMessage());
        return new ErrorDTO(GeneralChallengeException.CODE, ex.getMessage());
    }

    @ExceptionHandler(PriceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handlePriceNotFoundException(PriceNotFoundException ex) {
        log.error("Generic exception thrown - {}", ex.getMessage());
        return new ErrorDTO(PriceNotFoundException.CODE, ex.getMessage());
    }
}
