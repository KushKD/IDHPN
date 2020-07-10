package com.dit.himachal.validators;

import com.dit.himachal.entities.UserEntity;
import com.dit.himachal.CustomLogin.CustomUserService;
import com.dit.himachal.form.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Autowired
    private CustomUserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return RegisterUser.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RegisterUser user = (RegisterUser) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() <= 3 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.registerUser.username");
        }
        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.registerUser.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobileNumber", "NotEmpty");
        if (user.getMobileNumber().length() != 10 ) {
            errors.rejectValue("mobileNumber", "Size.registerUser.mobileNumber");
        }
        if (userService.findByMobileNumber(user.getMobileNumber()) != null) {
            errors.rejectValue("mobileNumber", "Duplicate.registerUser.mobileNumber");
        }


        if(user.getRoleId().equalsIgnoreCase("0")){
            errors.rejectValue("roleId", "Select.registerUser.roleId");
        }


        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() <= 7 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.registerUser.password");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm", "NotEmpty");
        if (!user.getPassword().equalsIgnoreCase(user.getPasswordConfirm())) {
            errors.rejectValue("passwordConfirm", "Diff.registerUser.passwordConfirm");
        }
    }
}
