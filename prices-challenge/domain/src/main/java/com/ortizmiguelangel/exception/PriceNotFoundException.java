package com.ortizmiguelangel.exception;

import com.ortizmiguelangel.exception.code.ExceptionCode;

public class PriceNotFoundException extends GeneralChallengeException {

    public static final String CODE = ExceptionCode.EXCEPTION_CODE_PRICE_NOT_FOUND;

    public PriceNotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public PriceNotFoundException(String errorMessage, Throwable e) {
        super(errorMessage, e);
    }
}
