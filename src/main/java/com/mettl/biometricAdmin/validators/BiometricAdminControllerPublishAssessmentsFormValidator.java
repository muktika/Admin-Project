package com.companyname.biometricAdmin.validators;

import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.metadata.BeanDescriptor;
import java.util.Set;
import java.util.regex.Pattern;

import com.companyname.biometricAdmin.model.BiometricAdminControllerPublishAssessmentsForm;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.impl.EmailValidator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by muktika on 21/12/15.
 */

@Named
public class BiometricAdminControllerPublishAssessmentsFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return BiometricAdminControllerPublishAssessmentsForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        final BiometricAdminControllerPublishAssessmentsForm form = (BiometricAdminControllerPublishAssessmentsForm) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companynameAssessmentId", "admin.assessment.assessmentid.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companynameAssessmentName", "admin.assessment.assessmentname.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "agencyId", "agency.id.empty");

    }
}


