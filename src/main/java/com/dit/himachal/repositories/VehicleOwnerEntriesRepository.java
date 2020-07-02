package com.dit.himachal.repositories;

import com.dit.himachal.entities.BarrierMaster;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.dit.himachal.entities.VehicleOwnerEntries;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VehicleOwnerEntriesRepository extends CrudRepository<VehicleOwnerEntries, Long>{
	
	@Query(value = "SELECT setval('mst_vehicle_owner_entries_vehicle_owner_id_seq',nextval('mst_vehicle_owner_entries_vehicle_owner_id_seq')-1);", nativeQuery =  true)
    Long getNextSeriesId();

    @Query(value="select * from public.mst_vehicle_owner_entries where id_card_number=:id_card_number AND vehicle_owner_mobile_number=:mobile_number AND vehicle_owner_vehicle_number=:vehicle_number", nativeQuery = true)
    VehicleOwnerEntries findVehiclaDetailsQrcodeScan(@Param("id_card_number") String id_card_number,
                                                @Param("mobile_number") Long mobile_number,
                                                @Param("vehicle_number") String vehicle_number);

    @Query(value="select * from public.mst_vehicle_owner_entries where  vehicle_owner_mobile_number=:mobile_number AND vehicle_owner_vehicle_number=:vehicle_number", nativeQuery = true)
    VehicleOwnerEntries SeachIDCArd(@Param("mobile_number") Long mobile_number,
                                                     @Param("vehicle_number") String vehicle_number);

}


