package com.companyname.biometricAdmin.validators;

import com.companyname.biometricAdmin.model.LoginForm;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.inject.Named;

/**
 * Created by muktika on 22/12/15.
 */


@Named
public class LoginFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return LoginForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        final LoginForm loginForm = (LoginForm) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "admin.email.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "admin.password.empty");

    }
}
