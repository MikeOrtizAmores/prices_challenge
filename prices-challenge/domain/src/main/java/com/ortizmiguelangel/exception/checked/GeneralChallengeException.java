package com.ortizmiguelangel.exception.checked;

import com.ortizmiguelangel.exception.checked.code.ExceptionCode;

import java.util.Objects;

public class GeneralChallengeException extends Exception {

    public static final String CODE = ExceptionCode.EXCEPTION_CODE_GENERAL;

    public GeneralChallengeException(String errorMessage, Throwable e) {
        super(CODE + ": " + errorMessage, e);
    }

    public GeneralChallengeException(String code, String errorMessage, Throwable e) {
        super(Objects.nonNull(code) ? code : CODE + ": " + errorMessage, e);
    }
}
