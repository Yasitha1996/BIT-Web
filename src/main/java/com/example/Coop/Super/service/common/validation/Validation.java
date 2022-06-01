package com.example.Coop.Super.service.common.validation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Validation {
    private final Log logger = LogFactory.getLog(getClass());
    public boolean isEmptyFieldValue(String value) {
        boolean isErrorField = true;
        try {
            if (value != null && !value.isEmpty()) {
                isErrorField = false;
            }
        } catch (Exception e) {
            logger.error("Exception : ", e);
        }
        return isErrorField;
    }

    public boolean checkSpecialCharacters(String input) {
        boolean status = false;
        if (!input.matches("[A-Za-z0-9]+")) {
            status = true;
        }
        return status;
    }
}
