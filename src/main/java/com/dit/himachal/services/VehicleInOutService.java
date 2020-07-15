package com.dit.himachal.services;

import com.dit.himachal.entities.BarrierMaster;
import com.dit.himachal.entities.VehicleInOutTrans;
import com.dit.himachal.entities.VehicleOwnerEntries;
import com.dit.himachal.repositories.BarrierRepository;
import com.dit.himachal.repositories.VehicleInOutTransRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleInOutService {
	
	@Autowired
	private VehicleInOutTransRepository vehicleInOutTransRepository;


	public VehicleInOutTransRepository getVehicleInOutTransRepository() {
		return vehicleInOutTransRepository;
	}

	public void setVehicleInOutTransRepository(VehicleInOutTransRepository vehicleInOutTransRepository) {
		this.vehicleInOutTransRepository = vehicleInOutTransRepository;
	}

	public VehicleInOutTrans saveVehicleInOutTrans(VehicleInOutTrans object) {
		VehicleInOutTrans savedData = vehicleInOutTransRepository.save(object);
		return savedData;
	}

	public List<VehicleInOutTrans> getTransactions(Integer id) {
		List<VehicleInOutTrans> data = vehicleInOutTransRepository.getallTransactions(id);
		return data;
	}

}
