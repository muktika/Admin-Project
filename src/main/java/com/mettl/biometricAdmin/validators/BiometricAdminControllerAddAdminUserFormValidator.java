package com.companyname.biometricAdmin.validators;
import com.companyname.biometricAdmin.model.BiometricAdminControllerAddAdminUserForm;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import javax.inject.Named;
import org.springframework.validation.Validator;

/**
 * Created by muktika on 4/1/16.
 */

@Named
public class BiometricAdminControllerAddAdminUserFormValidator implements Validator{
    @Override
    public boolean supports(Class<?> aClass) {
        return BiometricAdminControllerAddAdminUserForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        final BiometricAdminControllerAddAdminUserForm form = (BiometricAdminControllerAddAdminUserForm) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "admin.email.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "admin.password.empty");
        ValidationUtils.rejectIfEmpty(errors, "roleAllocations" , "admin.roles.empty");
    }
}
