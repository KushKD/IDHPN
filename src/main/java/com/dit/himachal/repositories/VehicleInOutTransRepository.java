package com.dit.himachal.repositories;

import com.dit.himachal.entities.BarrierMaster;
import com.dit.himachal.entities.VehicleInOutTrans;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleInOutTransRepository extends CrudRepository<VehicleInOutTrans, Long> {


}
