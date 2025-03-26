package com.irs.springbootflashattributeexample.util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public final class I18n {

    private final MessageSource messageSource;

    public String getMessage(String messageKey) {
        return messageSource.getMessage(messageKey, null, null);
    }

    public String getMessage(String messageKey, Object[] messageArgs) {
        return messageSource.getMessage(messageKey, messageArgs, null);
    }

    public String getMessage(String messageKey, Object[] messageArgs, Locale locale) {
        return messageSource.getMessage(messageKey, messageArgs, locale);
    }
}
