package com.dit.himachal.entities;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name = "mst_vehicle_owner_entries")
public class VehicleOwnerEntries implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7537163873024423738L;

    @Id
    @GeneratedValue(generator = "mst_vehicle_owner_entries_vehicle_owner_id_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "mst_vehicle_owner_entries_vehicle_owner_id_seq", sequenceName = "public.mst_vehicle_owner_entries_vehicle_owner_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "vehicle_owner_id")
    private Long vehicleOwnerId;

    @Column(name = "id_card_number")
    private String idCardNumber;

    @Column(name = "vehicle_owner_name")
    private String vehicleOwnerName;

    @Column(name = "vehicle_owner_imagename")
    private String vehicleOwnerImageName;

    @Column(name = "vehicle_owner_mobile_number")
    private Long vehicleOwnerMobileNumber;

    @Column(name = "id_valid_from")
    private String isValidFrom;

    @Column(name = "id_valid_upto")
    private String isValidUpto;

    @Column(name = "vehicle_owner_aadhaar_number")
    private String vehicleOwnerAadhaarNumber;

    @Column(name = "vehicle_owner_vehicle_number")
    private String vehicleOwnerVehicleNumber;

    @Column(name = "vehicle_owner_chassis_number")
    private String vehicleOwnerChassisNumber;

    @Column(name = "vehicle_owner_engine_number")
    private String vehicleOwnerEngineNumber;

    @Column(name = "vehicle_owner_driving_licence")
    private String vehicleOwnerDrivingLicence;

    @Column(name = "mobile_information")
    private String mobileInformation;

    @Column(name = "other_information")
    private String otherInformation;

    @Column(name = "data_entered_by")
    private Integer dataEnteredBy;

    @Column(name = "active")
    private boolean active;

    @Column(name = "id_generated")
    private boolean isGenerated;

    @OneToOne
    @JoinColumn(name = "barrier_id",  updatable = false)
    private BarrierMaster barriermaster;

    @OneToOne
    @JoinColumn(name = "district_id", updatable = false)
    private DistrictMaster districtMaster;

    @OneToOne
    @JoinColumn(name = "vehicle_type_id", updatable = false)
    private VehicleTypeMaster vehicleType;

    @OneToOne
    @JoinColumn(name = "vehicle_owner_type_id", updatable = false)
    private VehicleUserType vehicleUser;

    @Column(name = "vehicle_owner_address", updatable = false)
    private String vehicleOwnerAddress;

    @Column(name = "vehicle_driver_address", updatable = false)
    private String vehicleDriverAddress;


    @Column(name="card_created_on")
   private  String cardCreation;


    public Long getVehicleOwnerId() {
        return vehicleOwnerId;
    }

    public void setVehicleOwnerId(Long vehicleOwnerId) {
        this.vehicleOwnerId = vehicleOwnerId;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getVehicleOwnerName() {
        return vehicleOwnerName;
    }

    public void setVehicleOwnerName(String vehicleOwnerName) {
        this.vehicleOwnerName = vehicleOwnerName;
    }

    public String getVehicleOwnerImageName() {
        return vehicleOwnerImageName;
    }

    public void setVehicleOwnerImageName(String vehicleOwnerImageName) {
        this.vehicleOwnerImageName = vehicleOwnerImageName;
    }

    public Long getVehicleOwnerMobileNumber() {
        return vehicleOwnerMobileNumber;
    }

    public void setVehicleOwnerMobileNumber(Long vehicleOwnerMobileNumber) {
        this.vehicleOwnerMobileNumber = vehicleOwnerMobileNumber;
    }

    public String getIsValidFrom() {
        return isValidFrom;
    }

    public void setIsValidFrom(String isValidFrom) {
        this.isValidFrom = isValidFrom;
    }

    public String getIsValidUpto() {
        return isValidUpto;
    }

    public void setIsValidUpto(String isValidUpto) {
        this.isValidUpto = isValidUpto;
    }

    public String getVehicleOwnerAadhaarNumber() {
        return vehicleOwnerAadhaarNumber;
    }

    public void setVehicleOwnerAadhaarNumber(String vehicleOwnerAadhaarNumber) {
        this.vehicleOwnerAadhaarNumber = vehicleOwnerAadhaarNumber;
    }

    public String getVehicleOwnerVehicleNumber() {
        return vehicleOwnerVehicleNumber;
    }

    public void setVehicleOwnerVehicleNumber(String vehicleOwnerVehicleNumber) {
        this.vehicleOwnerVehicleNumber = vehicleOwnerVehicleNumber;
    }

    public String getVehicleOwnerChassisNumber() {
        return vehicleOwnerChassisNumber;
    }

    public void setVehicleOwnerChassisNumber(String vehicleOwnerChassisNumber) {
        this.vehicleOwnerChassisNumber = vehicleOwnerChassisNumber;
    }

    public String getVehicleOwnerEngineNumber() {
        return vehicleOwnerEngineNumber;
    }

    public void setVehicleOwnerEngineNumber(String vehicleOwnerEngineNumber) {
        this.vehicleOwnerEngineNumber = vehicleOwnerEngineNumber;
    }

    public String getVehicleOwnerDrivingLicence() {
        return vehicleOwnerDrivingLicence;
    }

    public void setVehicleOwnerDrivingLicence(String vehicleOwnerDrivingLicence) {
        this.vehicleOwnerDrivingLicence = vehicleOwnerDrivingLicence;
    }

    public String getMobileInformation() {
        return mobileInformation;
    }

    public void setMobileInformation(String mobileInformation) {
        this.mobileInformation = mobileInformation;
    }

    public String getOtherInformation() {
        return otherInformation;
    }

    public void setOtherInformation(String otherInformation) {
        this.otherInformation = otherInformation;
    }

    public Integer getDataEnteredBy() {
        return dataEnteredBy;
    }

    public void setDataEnteredBy(Integer dataEnteredBy) {
        this.dataEnteredBy = dataEnteredBy;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isGenerated() {
        return isGenerated;
    }

    public void setGenerated(boolean generated) {
        isGenerated = generated;
    }

    public BarrierMaster getBarriermaster() {
        return barriermaster;
    }

    public void setBarriermaster(BarrierMaster barriermaster) {
        this.barriermaster = barriermaster;
    }

    public DistrictMaster getDistrictMaster() {
        return districtMaster;
    }

    public void setDistrictMaster(DistrictMaster districtMaster) {
        this.districtMaster = districtMaster;
    }

    public VehicleTypeMaster getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleTypeMaster vehicleType) {
        this.vehicleType = vehicleType;
    }

    public VehicleUserType getVehicleUser() {
        return vehicleUser;
    }

    public void setVehicleUser(VehicleUserType vehicleUser) {
        this.vehicleUser = vehicleUser;
    }

	public String getCardCreation() {
		return cardCreation;
	}

	public void setCardCreation(String cardCreation) {
		this.cardCreation = cardCreation;
	}

	public String getVehicleOwnerAddress() {
		return vehicleOwnerAddress;
	}

	public void setVehicleOwnerAddress(String vehicleOwnerAddress) {
		this.vehicleOwnerAddress = vehicleOwnerAddress;
	}

	public String getVehicleDriverAddress() {
		return vehicleDriverAddress;
	}

	public void setVehicleDriverAddress(String vehicleDriverAddress) {
		this.vehicleDriverAddress = vehicleDriverAddress;
	}

	@Override
	public String toString() {
		return "VehicleOwnerEntries{" +
				"vehicleOwnerId=" + vehicleOwnerId +
				", idCardNumber='" + idCardNumber + '\'' +
				", vehicleOwnerName='" + vehicleOwnerName + '\'' +
				", vehicleOwnerImageName='" + vehicleOwnerImageName + '\'' +
				", vehicleOwnerMobileNumber=" + vehicleOwnerMobileNumber +
				", isValidFrom='" + isValidFrom + '\'' +
				", isValidUpto='" + isValidUpto + '\'' +
				", vehicleOwnerAadhaarNumber='" + vehicleOwnerAadhaarNumber + '\'' +
				", vehicleOwnerVehicleNumber='" + vehicleOwnerVehicleNumber + '\'' +
				", vehicleOwnerChassisNumber='" + vehicleOwnerChassisNumber + '\'' +
				", vehicleOwnerEngineNumber='" + vehicleOwnerEngineNumber + '\'' +
				", vehicleOwnerDrivingLicence='" + vehicleOwnerDrivingLicence + '\'' +
				", mobileInformation='" + mobileInformation + '\'' +
				", otherInformation='" + otherInformation + '\'' +
				", dataEnteredBy=" + dataEnteredBy +
				", active=" + active +
				", isGenerated=" + isGenerated +
				", barriermaster=" + barriermaster +
				", districtMaster=" + districtMaster +
				", vehicleType=" + vehicleType +
				", vehicleUser=" + vehicleUser +
				", vehicleOwnerAddress='" + vehicleOwnerAddress + '\'' +
				", vehicleDriverAddress='" + vehicleDriverAddress + '\'' +
				", cardCreation='" + cardCreation + '\'' +
				'}';
	}
}
