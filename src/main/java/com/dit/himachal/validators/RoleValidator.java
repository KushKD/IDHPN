package com.dit.himachal.validators;

import com.dit.himachal.CustomLogin.CustomUserService;
import com.dit.himachal.form.RegisterUser;
import com.dit.himachal.form.RolesForm;
import com.dit.himachal.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RoleValidator implements Validator {
    @Autowired
    private CustomUserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public boolean supports(Class<?> aClass) {
        return RolesForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RolesForm user = (RolesForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roleName", "NotEmpty");
        if (user.getRoleName().length() <= 3 || user.getRoleName().length() > 32) {
            errors.rejectValue("roleName", "Size.rolesForm.roleName");
        }
        if (roleService.checkRoleName(user.getRoleName()) != null) {
            errors.rejectValue("roleName", "Duplicate.rolesForm.roleName");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roleDescription", "NotEmpty");
        if (user.getRoleDescription().length() <= 3 || user.getRoleDescription().length() >40 ) {
            errors.rejectValue("roleDescription", "Size.rolesForm.roleDescription");
        }




    }
}
