package com.dit.himachal.validators;

import com.dit.himachal.form.SearchID;
import com.dit.himachal.form.showIdCardList;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SearchIdCardValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return SearchID.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SearchID idcard = (SearchID) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "vehicleNumber", "NotEmpty");
        if (idcard.getVehicleNumber()== null || idcard.getVehicleNumber().isEmpty()) {
            errors.rejectValue("vehicleNumber", "Size.searchId.vehicleNumber");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobileNumber", "NotEmpty");
        if (idcard.getMobileNumber()== null || idcard.getMobileNumber().isEmpty()) {
            errors.rejectValue("mobileNumber", "Size.searchId.mobileNumber");
        }

        if (idcard.getMobileNumber().length()!=10) {
            errors.rejectValue("mobileNumber", "Length.searchId.mobileNumber");
        }



    }
}
