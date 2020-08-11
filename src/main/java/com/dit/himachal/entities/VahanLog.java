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

	@Column(name = "is_active")
	private boolean isActive;


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
				", isActive=" + isActive +
				'}';
	}
}
