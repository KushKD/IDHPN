package com.dit.himachal.validators;

import com.dit.himachal.CustomLogin.CustomUserService;
import com.dit.himachal.form.RegisterUser;
import com.dit.himachal.form.VahanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class VahanServiceValidator implements Validator {
    @Autowired
    private CustomUserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return VahanService.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        VahanService user = (VahanService) o;



        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "parameter", "NotEmpty");



        if(user.getServiceType().equalsIgnoreCase("")){
            errors.rejectValue("serviceType", "Select.vehicleService.serviceType");
        }


    }
}
