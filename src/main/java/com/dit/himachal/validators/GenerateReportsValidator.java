package com.dit.himachal.validators;

import com.dit.himachal.form.ReportsForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class GenerateReportsValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return ReportsForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ReportsForm idcard = (ReportsForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "districtId", "NotEmpty");
        if (idcard.getDistrictId()== null || idcard.getDistrictId().isEmpty()) {
            errors.rejectValue("districtId", "Size.reportsForm.district");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "barrierId", "NotEmpty");
        if (idcard.getBarrierId()== null || idcard.getBarrierId().isEmpty()) {
            errors.rejectValue("barrierId", "Size.reportsForm.barrier");
        }



    }
}
