package com.irs.springbootflashattributeexample.presentacion.validadores;

import com.irs.springbootflashattributeexample.presentacion.comandos.CustomerEditCommand;
import com.irs.springbootflashattributeexample.util.I18n;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Objects;
import java.util.regex.Pattern;

@Component("customerEditCommandValidator")
@RequiredArgsConstructor
public class CustomerEditCommandValidator implements Validator {

    public static final String FN_FIRST_NAME = "firstName";
    public static final String FN_LAST_NAME = "lastName";
    public static final String FN_AGE = "age";
    public static final String FN_EMAIL = "email";

    private final I18n i18n;

    @Override
    public boolean supports(Class<?> clazz) {
        return CustomerEditCommand.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        // Valido el campo first name (requerido).
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                FN_FIRST_NAME,
                ConstValidator.ERROR_CODE_REQUIRED,
                new Object[] { i18n.getMessage("label.first.name") });

        // Valido el campo last name (requerido).
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                FN_LAST_NAME,
                ConstValidator.ERROR_CODE_REQUIRED,
                new Object[] { i18n.getMessage("label.last.name") });

        // Valido el campo age (requerido, número rango 1-120).
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                FN_AGE,
                ConstValidator.ERROR_CODE_REQUIRED,
                new Object[] { i18n.getMessage("label.age") });
        if (!errors.hasFieldErrors(FN_AGE)) {
            rejectIfAgeInvalid(errors);
        }

        // Valido el campo email (requerido, email).
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                FN_EMAIL,
                ConstValidator.ERROR_CODE_REQUIRED,
                new Object[] { i18n.getMessage("label.email") });
        if (!errors.hasFieldErrors(FN_EMAIL)) {
            rejectIfEmailInvalid(errors);
        }
    }

    private void rejectIfAgeInvalid(Errors errors) {
        Object ageValue = errors.getFieldValue(FN_AGE);
        Object[] errorArgs = new Object[] { i18n.getMessage("label.age"), ConstValidator.AGE_MIN, ConstValidator.AGE_MAX };
        try {
            if (!Objects.isNull(ageValue)) {
                Long ageNumberValue = Long.valueOf((String) ageValue);
                if (ageNumberValue < ConstValidator.AGE_MIN || ageNumberValue > ConstValidator.AGE_MAX) {
                    errors.rejectValue(FN_AGE, ConstValidator.ERROR_CODE_NUMBER_LONG_RANGE, errorArgs, null);
                }
            } else {
                errors.rejectValue(FN_AGE, "typeMismatch.age");
            }
        } catch (ClassCastException | NumberFormatException e) {
            errors.rejectValue(FN_AGE, "typeMismatch.age");
        }
    }

    private void rejectIfEmailInvalid(Errors errors) {
        Object emailValue = errors.getFieldValue(FN_EMAIL);
        String emailValueString = null;
        if (emailValue instanceof String) {
            emailValueString = (String) emailValue;
        } else {
            emailValueString = emailValue.toString();
        }
        if (!Pattern.matches(ConstValidator.EMAIL_REGEXP, emailValueString)) {
            errors.rejectValue(FN_EMAIL, ConstValidator.ERROR_CODE_EMAIL, new Object[] { i18n.getMessage("label.email") }, null);
        }
    }
}
