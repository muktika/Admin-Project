package com.companyname.biometricAdmin.validators;

import com.companyname.biometricAdmin.model.BiometricAdminControllerPublishAgencyForm;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import javax.inject.Named;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by dell on 23/12/15.
 */

@Named
public class BiometricAdminControllerPublishAgencyFormValidator implements Validator{

    @Override
    public boolean supports(Class<?> aClass) {
        return BiometricAdminControllerPublishAgencyForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        final BiometricAdminControllerPublishAgencyForm form = (BiometricAdminControllerPublishAgencyForm) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "agencyName", "agency.name.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "publicKey", "public.key.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "privateKey", "private.key.empty");


    }
}
