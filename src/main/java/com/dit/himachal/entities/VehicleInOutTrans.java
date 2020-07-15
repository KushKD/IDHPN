package com.dit.himachal.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

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

//	@Column(name = "vehicle_owner_id")
//	private Integer vehicleOwnerId;

	@Column(name = "captured_by")
	private Integer capturedBy;

	@OneToOne
	@JoinColumn(name = "vehicle_owner_id", updatable = false)
	private VehicleOwnerEntries vehicleOwnerDetails;

//	@OneToOne
//	@JoinColumn(name = "captured_by")
//	private UserEntity capturedBy;

	@OneToOne
	@JoinColumn(name = "barrier_id", updatable = false)
	private BarrierMaster barriermaster;

	@Column(name = "latitude")
	private Double latitude;

	@Column(name = "longitude")
	private Double longitude;

	@Column(name = "is_active")
	private boolean isActive;

	@Column(name = "remarks")
	private String remarks;


	public Long getTransId() {
		return transId;
	}

	public void setTransId(Long transId) {
		this.transId = transId;
	}

	public VehicleOwnerEntries getVehicleOwnerDetails() {
		return vehicleOwnerDetails;
	}

	public void setVehicleOwnerDetails(VehicleOwnerEntries vehicleOwnerDetails) {
		this.vehicleOwnerDetails = vehicleOwnerDetails;
	}

//	public UserEntity getUserentity() {
//		return userentity;
//	}
//
//	public void setUserentity(UserEntity userentity) {
//		this.userentity = userentity;
//	}

	public BarrierMaster getBarriermaster() {
		return barriermaster;
	}

	public void setBarriermaster(BarrierMaster barriermaster) {
		this.barriermaster = barriermaster;
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

//	public Integer getCapturedBy() {
//		return capturedBy;
//	}
//
//	public void setCapturedBy(Integer capturedBy) {
//		this.capturedBy = capturedBy;
//	}


	public Integer getCapturedBy() {
		return capturedBy;
	}

	public void setCapturedBy(Integer capturedBy) {
		this.capturedBy = capturedBy;
	}



	@Override
	public String toString() {
		return "VehicleInOutTrans{" +
				"transId=" + transId +
				", capturedBy=" + capturedBy +
				", vehicleOwnerDetails=" + vehicleOwnerDetails +
				", barriermaster=" + barriermaster +
				", latitude=" + latitude +
				", longitude=" + longitude +
				", isActive=" + isActive +
				", remarks='" + remarks + '\'' +
				'}';
	}
}
