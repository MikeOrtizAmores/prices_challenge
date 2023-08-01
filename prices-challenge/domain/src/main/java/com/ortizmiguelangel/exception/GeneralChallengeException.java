package com.ortizmiguelangel.exception;

import com.ortizmiguelangel.exception.code.ExceptionCode;

public class GeneralChallengeException extends RuntimeException {

    public static final String CODE = ExceptionCode.EXCEPTION_CODE_GENERAL;

    public GeneralChallengeException(String errorMessage) {
        super(errorMessage);
    }

    public GeneralChallengeException(String errorMessage, Throwable e) {
        super(errorMessage, e);
    }
}
