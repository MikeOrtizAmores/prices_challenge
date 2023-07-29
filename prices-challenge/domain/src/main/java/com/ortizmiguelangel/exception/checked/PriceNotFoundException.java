package com.ortizmiguelangel.exception.checked;

import com.ortizmiguelangel.exception.checked.code.ExceptionCode;

public class PriceNotFoundException extends GeneralChallengeException {

    public static final String CODE = ExceptionCode.EXCEPTION_CODE_PRICE_NOT_FOUND;

    public PriceNotFoundException(Throwable e) {
        super(CODE, e);
    }

    public PriceNotFoundException(String errorMessage, Throwable e) {
        super(CODE, errorMessage, e);
    }
}
