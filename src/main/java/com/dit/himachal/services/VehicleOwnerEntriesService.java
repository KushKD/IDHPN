package com.dit.himachal.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dit.himachal.entities.VehicleOwnerEntries;
import com.dit.himachal.repositories.VehicleOwnerEntriesRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleOwnerEntriesService {
	
	@Autowired
	private VehicleOwnerEntriesRepository vehicleOwnerEntriesRepository;

	public VehicleOwnerEntriesRepository getVehicleOwnerEntriesRepository() {
		return vehicleOwnerEntriesRepository;
	}

	public void setVehicleOwnerEntriesRepository(VehicleOwnerEntriesRepository vehicleOwnerEntriesRepository) {
		this.vehicleOwnerEntriesRepository = vehicleOwnerEntriesRepository;
	}
	
	public Long saveVehicleOwnerEntries(VehicleOwnerEntries object) {
		VehicleOwnerEntries savedData = vehicleOwnerEntriesRepository.save(object);
		return savedData.getVehicleOwnerId();
	}
	
	public Long getIdCardNumberSequence() {
		return  vehicleOwnerEntriesRepository.getNextSeriesId();
		
	}

	public Optional<VehicleOwnerEntries> getOwnerDetails(Long id) {

		return  vehicleOwnerEntriesRepository.findById(id);

	}

	public VehicleOwnerEntries scanQrCodeDate(Long mobile, String id_card, String vehicle_number) {

		return  vehicleOwnerEntriesRepository.findVehiclaDetailsQrcodeScan(id_card,mobile,vehicle_number);

	}

	public VehicleOwnerEntries searchIdentity(Long mobile, String vehicle_number) {

		return  vehicleOwnerEntriesRepository.SeachIDCArd(mobile,vehicle_number);

	}

	public List<VehicleOwnerEntries> searchIdentityList(Long mobile, String vehicle_number) {

		return  vehicleOwnerEntriesRepository.SeachIDCArdList(mobile,vehicle_number);

	}

	public List<VehicleOwnerEntries> getDataViaDistrictBarrier(Integer districtId, Integer BarrierId, String date) {
		return  vehicleOwnerEntriesRepository.getDataviaDistrictBarrierDate(districtId,BarrierId,date);

	}

	public List<VehicleOwnerEntries> getReportAllParameters(Integer districtId, Integer barrierId, Integer vehicleType, Integer OwnerType, String fromDate, String toDate){
		return vehicleOwnerEntriesRepository.getReportAllParams(districtId,barrierId,vehicleType,OwnerType,fromDate,toDate);
	}

	public List<VehicleOwnerEntries> getReportAllParametersNoOwner(Integer districtId, Integer barrierId, Integer vehicleType, String fromDate, String toDate){
		return vehicleOwnerEntriesRepository.getReportParamsNoOwner(districtId,barrierId,vehicleType,fromDate,toDate);
	}


	public List<VehicleOwnerEntries> getReportAllParametersOwnerDates(Integer districtId, Integer barrierId, Integer ownerType, String fromDate, String toDate){
		return vehicleOwnerEntriesRepository.getReportAllParametersOwnerDates(districtId,barrierId,ownerType,fromDate,toDate);
	}

	public List<VehicleOwnerEntries> getReportDistrictBarrierToFrom(Integer districtId, Integer barrierId, String fromDate, String toDate){
		return vehicleOwnerEntriesRepository.getReportDistrictBarrierFromTwo(districtId,barrierId,fromDate,toDate);
	}

	public List<VehicleOwnerEntries> getReportDistrictBarrierFrom(Integer districtId, Integer barrierId, String fromDate){
		return vehicleOwnerEntriesRepository.getReportDistrictBarrierFrom(districtId,barrierId,fromDate);
	}

	public List<VehicleOwnerEntries> getReportDistrictBarrier(Integer districtId, Integer barrierId){
		return vehicleOwnerEntriesRepository.getReportDistrictBarrier(districtId,barrierId);
	}

	//getReportDistrictBarrierVehicleType
	public List<VehicleOwnerEntries> getReportDistrictBarrierVehicleType(Integer districtId, Integer barrierId,Integer vehicleType){
		return vehicleOwnerEntriesRepository.getReportDistrictBarrierVehicleType(districtId,barrierId, vehicleType);
	}


	public List<VehicleOwnerEntries> getReportDistrictBarrierOwnerTypeType(Integer districtId, Integer barrierId,Integer vehicleType){
		return vehicleOwnerEntriesRepository.getReportDistrictBarrierOwnerTypeType(districtId,barrierId, vehicleType);
	}

	//getReportDistrictBarrierOwnerTypeVehicleType
	public List<VehicleOwnerEntries> getReportDistrictBarrierOwnerTypeVehicleType(Integer districtId, Integer barrierId,Integer vehicleType, Integer ownerType){
		return vehicleOwnerEntriesRepository.getReportDistrictBarrierOwnerTypeVehicleType(districtId,barrierId, vehicleType, ownerType);
	}



}
