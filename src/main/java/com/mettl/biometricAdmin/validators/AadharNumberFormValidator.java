package com.companyname.biometricAdmin.validators;

import com.companyname.biometricAdmin.model.AadharNumberForm;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.inject.Named;

/**
 * Created by muktika on 23/12/15.
 */

@Named
public class AadharNumberFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return AadharNumberForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        final AadharNumberForm form = (AadharNumberForm) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "aadharNumber", "aadhar.number.empty");

    }
}
