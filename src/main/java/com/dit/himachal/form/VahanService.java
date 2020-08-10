package com.dit.himachal.form;

import java.io.Serializable;

public class VahanService implements Serializable {

    private String serviceType;
    private String parameter;

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    @Override
    public String toString() {
        return "VahanService{" +
                "serviceType='" + serviceType + '\'' +
                ", parameter='" + parameter + '\'' +
                '}';
    }
}
