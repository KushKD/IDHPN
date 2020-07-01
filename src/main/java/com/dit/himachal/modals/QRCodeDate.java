package com.dit.himachal.modals;

import java.io.Serializable;

public class QRCodeDate implements Serializable {

    private Long mobile_number;
    private String id_card_number;
    private String vehicle_number;

    public Long getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(Long mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getId_card_number() {
        return id_card_number;
    }

    public void setId_card_number(String id_card_number) {
        this.id_card_number = id_card_number;
    }

    public String getVehicle_number() {
        return vehicle_number;
    }

    public void setVehicle_number(String vehicle_number) {
        this.vehicle_number = vehicle_number;
    }

    @Override
    public String toString() {
        return "QRCodeDate{" +
                "mobile_number=" + mobile_number +
                ", id_card_number='" + id_card_number + '\'' +
                ", vehicle_number='" + vehicle_number + '\'' +
                '}';
    }
}
