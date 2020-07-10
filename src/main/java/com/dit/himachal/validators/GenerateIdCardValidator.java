package com.dit.himachal.validators;

import com.dit.himachal.CustomLogin.CustomUserService;
import com.dit.himachal.form.RegisterUser;
import com.dit.himachal.form.showIdCardList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class GenerateIdCardValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return showIdCardList.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        showIdCardList idcard = (showIdCardList) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date", "NotEmpty");
        if (idcard.getDate()== null || idcard.getDate().isEmpty()) {
            errors.rejectValue("date", "Size.showIdCardList.date");
        }

        if(idcard.getDistrict_id().equalsIgnoreCase("0") || idcard.getDistrict_id().isEmpty() || idcard.getDistrict_id()==null){
            errors.rejectValue("district_id", "Select.showIdCardList.district_id");
        }

        if(idcard.getBarrier_id().equalsIgnoreCase("0") || idcard.getBarrier_id().isEmpty() || idcard.getBarrier_id()==null){
            errors.rejectValue("barrier_id", "Select.showIdCardList.barrier_id");
        }

    }
}
