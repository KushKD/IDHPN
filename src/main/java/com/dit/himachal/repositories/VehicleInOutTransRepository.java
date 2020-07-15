package com.dit.himachal.repositories;

import com.dit.himachal.entities.BarrierMaster;
import com.dit.himachal.entities.VehicleInOutTrans;
import com.dit.himachal.entities.VehicleOwnerEntries;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleInOutTransRepository extends CrudRepository<VehicleInOutTrans, Long> {

      @Query(value="select * from public.vehicle_in_out_trans where  vehicle_owner_id=:id AND is_active=true", nativeQuery = true)
      List<VehicleInOutTrans> getallTransactions (@Param("id") Integer id);



}
