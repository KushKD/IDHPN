package com.dit.himachal.form;

import java.io.Serializable;

public class SaarthiForm implements Serializable {

    private String dlNumber;

    public String getDlNumber() {
        return dlNumber;
    }

    public void setDlNumber(String dlNumber) {
        this.dlNumber = dlNumber;
    }

    @Override
    public String toString() {
        return "SaarthiForm{" +
                "dlNumber='" + dlNumber + '\'' +
                '}';
    }
}
