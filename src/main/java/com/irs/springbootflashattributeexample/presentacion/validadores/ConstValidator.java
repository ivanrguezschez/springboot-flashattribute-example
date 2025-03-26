package com.irs.springbootflashattributeexample.presentacion.validadores;

public final class ConstValidator {

    private ConstValidator() {}

    public static final String ERROR_CODE_REQUIRED = "required";
    public static final String ERROR_CODE_INVALID = "invalid";
    public static final String ERROR_CODE_NUMBER_POSITIVE = "numberPositive";
    public static final String ERROR_CODE_NUMBER_NEGATIVE = "numberNegative";
    public static final String ERROR_CODE_NUMBER_ZERO_OR_POSITIVE = "numberZeroOrPositive";
    public static final String ERROR_CODE_NUMBER_ZERO_OR_NEGATIVE = "numberZeroOrNegative";
    public static final String ERROR_CODE_NUMBER_LONG_RANGE = "numberLongRange";
    public static final String ERROR_CODE_NUMBER_DOUBLE_RANGE = "numberDoubleRange";
    public static final String ERROR_CODE_NUMBER_RANGE_AND_NOT_ZERO = "numberRangeAndNotZero";
    public static final String ERROR_CODE_DATE = "date";
    public static final String ERROR_CODE_EMAIL = "email";

    public static final Long AGE_MIN = Long.valueOf(1L);
    public static final Long AGE_MAX = Long.valueOf(120L);

    public static final String EMAIL_REGEXP = "^[A-Za-z0-9._%'-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,4}$";
}
