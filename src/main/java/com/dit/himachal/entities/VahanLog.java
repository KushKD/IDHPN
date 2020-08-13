package com.dit.himachal.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "log_vahan_service")
public class VahanLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3440391786169092855L;

	@Id
	@GeneratedValue(generator = "log_vahan_service_log_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "log_vahan_service_log_id_seq", sequenceName = "public.log_vahan_service_log_id_seq", initialValue = 1, allocationSize = 1)
	@Column(name = "log_id")
	private Long logId;

	@Column(name = "log_user_id")
	private Long logUserId;
	
	@Column(name = "log_ip_address")
	private String logIpAddress;

	@Column(name = "log_service_response_code")
	private Integer logServiceResponseCode;

	@Column(name = "log_vehicle_number")
	private String logVehicleNumber;

	@Column(name = "log_application_name")
	private String logApplicationName;

	@Column(name = "log_function_name")
	private String logFunctionName;

	//New Feilds
	@Column(name = "engine_number")
	private String engineNumber;

	@Column(name = "chassis_number")
	private String chassisNumber;

	@Column(name = "rc_status")
	private String rcStatus;

	@Column(name = "rc_registered_at")
	private String rcRegisteredAt;

	@Column(name = "reg_no")
	private String regNo;

	@Column(name = "rc_fit_upto")
	private String rcFitUpto;

	@Column(name = "rc_status_as_on_date")
	private String rcStatusAsOnDate;

	@Column(name = "is_active")
	private boolean isActive;

	@Column(name = "rc_owner_name")
	private String rcOwnerName;


	public String getRcOwnerName() {
		return rcOwnerName;
	}

	public void setRcOwnerName(String rcOwnerName) {
		this.rcOwnerName = rcOwnerName;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public Long getLogUserId() {
		return logUserId;
	}

	public void setLogUserId(Long logUserId) {
		this.logUserId = logUserId;
	}

	public String getLogIpAddress() {
		return logIpAddress;
	}

	public void setLogIpAddress(String logIpAddress) {
		this.logIpAddress = logIpAddress;
	}

	public Integer getLogServiceResponseCode() {
		return logServiceResponseCode;
	}

	public void setLogServiceResponseCode(Integer logServiceResponseCode) {
		this.logServiceResponseCode = logServiceResponseCode;
	}

	public String getLogVehicleNumber() {
		return logVehicleNumber;
	}

	public void setLogVehicleNumber(String logVehicleNumber) {
		this.logVehicleNumber = logVehicleNumber;
	}

	public String getLogApplicationName() {
		return logApplicationName;
	}

	public void setLogApplicationName(String logApplicationName) {
		this.logApplicationName = logApplicationName;
	}

	public String getLogFunctionName() {
		return logFunctionName;
	}

	public void setLogFunctionName(String logFunctionName) {
		this.logFunctionName = logFunctionName;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	public String getEngineNumber() {
		return engineNumber;
	}

	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}

	public String getChassisNumber() {
		return chassisNumber;
	}

	public void setChassisNumber(String chassisNumber) {
		this.chassisNumber = chassisNumber;
	}

	public String getRcStatus() {
		return rcStatus;
	}

	public void setRcStatus(String rcStatus) {
		this.rcStatus = rcStatus;
	}

	public String getRcRegisteredAt() {
		return rcRegisteredAt;
	}

	public void setRcRegisteredAt(String rcRegisteredAt) {
		this.rcRegisteredAt = rcRegisteredAt;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getRcFitUpto() {
		return rcFitUpto;
	}

	public void setRcFitUpto(String rcFitUpto) {
		this.rcFitUpto = rcFitUpto;
	}

	public String getRcStatusAsOnDate() {
		return rcStatusAsOnDate;
	}

	public void setRcStatusAsOnDate(String rcStatusAsOnDate) {
		this.rcStatusAsOnDate = rcStatusAsOnDate;
	}

	@Override
	public String toString() {
		return "VahanLog{" +
				"logId=" + logId +
				", logUserId=" + logUserId +
				", logIpAddress='" + logIpAddress + '\'' +
				", logServiceResponseCode=" + logServiceResponseCode +
				", logVehicleNumber='" + logVehicleNumber + '\'' +
				", logApplicationName='" + logApplicationName + '\'' +
				", logFunctionName='" + logFunctionName + '\'' +
				", engineNumber='" + engineNumber + '\'' +
				", chassisNumber='" + chassisNumber + '\'' +
				", rcStatus='" + rcStatus + '\'' +
				", rcRegisteredAt='" + rcRegisteredAt + '\'' +
				", regNo='" + regNo + '\'' +
				", rcFitUpto='" + rcFitUpto + '\'' +
				", rcStatusAsOnDate='" + rcStatusAsOnDate + '\'' +
				", isActive=" + isActive +
				", rcOwnerName='" + rcOwnerName + '\'' +
				'}';
	}
}
