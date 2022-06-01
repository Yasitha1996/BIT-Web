package com.example.Coop.Super.validator;

import org.springframework.validation.BindingResult;

@FunctionalInterface
public interface RequestBeanValidation<T> {

    BindingResult validateRequestBean(T t);
}
