package com.dit.himachal.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "vehicle_in_out_trans")
public class VehicleInOutTrans implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3440391712168692855L;

	@Id
	@GeneratedValue(generator = "vehicle_in_out_trans_in_out_trans_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "vehicle_in_out_trans_in_out_trans_id_seq", sequenceName = "public.vehicle_in_out_trans_in_out_trans_id_seq", initialValue = 1, allocationSize = 1)
	@Column(name = "in_out_trans_id")
	private Long transId;

	@Column(name = "vehicle_owner_id")
	private Integer vehicleOwnerId;
	
	@Column(name = "captured_by")
	private Integer capturedBy;

	@Column(name = "barrier_id")
	private Integer barrierId;

	@Column(name = "latitude")
	private Double latitude;

	@Column(name = "longitude")
	private Double longitude;

	@Column(name = "longitude")
	private boolean isActive;

	@Column(name = "longitude")
	private String remarks;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getTransId() {
		return transId;
	}

	public void setTransId(Long transId) {
		this.transId = transId;
	}

	public Integer getVehicleOwnerId() {
		return vehicleOwnerId;
	}

	public void setVehicleOwnerId(Integer vehicleOwnerId) {
		this.vehicleOwnerId = vehicleOwnerId;
	}

	public Integer getCapturedBy() {
		return capturedBy;
	}

	public void setCapturedBy(Integer capturedBy) {
		this.capturedBy = capturedBy;
	}

	public Integer getBarrierId() {
		return barrierId;
	}

	public void setBarrierId(Integer barrierId) {
		this.barrierId = barrierId;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "VehicleInOutTrans{" +
				"transId=" + transId +
				", vehicleOwnerId='" + vehicleOwnerId + '\'' +
				", capturedBy=" + capturedBy +
				", barrierId=" + barrierId +
				", latitude=" + latitude +
				", longitude=" + longitude +
				", isActive=" + isActive +
				", remarks='" + remarks + '\'' +
				'}';
	}
}
