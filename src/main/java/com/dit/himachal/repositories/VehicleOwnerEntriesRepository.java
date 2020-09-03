package com.dit.himachal.repositories;

import com.dit.himachal.entities.BarrierMaster;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.dit.himachal.entities.VehicleOwnerEntries;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VehicleOwnerEntriesRepository extends CrudRepository<VehicleOwnerEntries, Long> {

    @Query(value = "SELECT setval('mst_vehicle_owner_entries_vehicle_owner_id_seq',nextval('mst_vehicle_owner_entries_vehicle_owner_id_seq')-1);", nativeQuery = true)
    Long getNextSeriesId();

    @Query(value = "select * from public.mst_vehicle_owner_entries where id_card_number=:id_card_number AND vehicle_owner_mobile_number=:mobile_number AND vehicle_owner_vehicle_number=:vehicle_number AND active = true", nativeQuery = true)
    VehicleOwnerEntries findVehiclaDetailsQrcodeScan(@Param("id_card_number") String id_card_number,
                                                     @Param("mobile_number") Long mobile_number,
                                                     @Param("vehicle_number") String vehicle_number);

    @Query(value = "select * from public.mst_vehicle_owner_entries where  vehicle_owner_mobile_number=:mobile_number AND vehicle_owner_vehicle_number=:vehicle_number AND active = true order by createddate desc", nativeQuery = true)
    VehicleOwnerEntries SeachIDCArd(@Param("mobile_number") Long mobile_number, @Param("vehicle_number") String vehicle_number);

    @Query(value = "select * from public.mst_vehicle_owner_entries where  vehicle_owner_mobile_number=:mobile_number AND vehicle_owner_vehicle_number=:vehicle_number AND active = true order by createddate desc", nativeQuery = true)
    List<VehicleOwnerEntries> SeachIDCArdList(@Param("mobile_number") Long mobile_number, @Param("vehicle_number") String vehicle_number);


    @Query(value = "select * from public.mst_vehicle_owner_entries where  district_id=:districtId AND barrier_id=:barrierId AND active = true AND  to_char(createddate, 'DD/MM/YYYY') = :selectedDate order by createddate desc", nativeQuery = true)
    List<VehicleOwnerEntries> getDataviaDistrictBarrierDate(@Param("districtId") Integer districtId,
                                                            @Param("barrierId") Integer barrierId, @Param("selectedDate") String date);

    @Query(value = "select * from public.mst_vehicle_owner_entries where  district_id=:districtId AND barrier_id=:barrierId AND vehicle_type_id=:vehicleType AND vehicle_owner_type_id=:ownerType AND active = true AND to_char(createddate, 'DD-MM-YYYY') >= :fromDate AND to_char(createddate, 'DD-MM-YYYY') <= :toDate order by createddate desc", nativeQuery = true)
    List<VehicleOwnerEntries> getReportAllParams(@Param("districtId") Integer districtId,
                                                 @Param("barrierId") Integer barrierId,
                                                 @Param("vehicleType") Integer vehicleType,
                                                 @Param("ownerType") Integer ownerType,
                                                 @Param("fromDate") String fromDate,
                                                 @Param("toDate") String toDate);

    @Query(value = "select * from public.mst_vehicle_owner_entries where  district_id=:districtId AND barrier_id=:barrierId AND vehicle_type_id=:vehicleType  AND active = true AND to_char(createddate, 'DD-MM-YYYY') >= :fromDate AND to_char(createddate, 'DD-MM-YYYY') <= :toDate order by createddate desc", nativeQuery = true)
    List<VehicleOwnerEntries> getReportParamsNoOwner(@Param("districtId") Integer districtId,
                                                 @Param("barrierId") Integer barrierId,
                                                 @Param("vehicleType") Integer vehicleType,
                                                 @Param("fromDate") String fromDate,
                                                 @Param("toDate") String toDate);


    @Query(value = "select * from public.mst_vehicle_owner_entries where  district_id=:districtId AND barrier_id=:barrierId AND vehicle_owner_type_id=:ownerType  AND active = true AND to_char(createddate, 'DD-MM-YYYY') >= :fromDate AND to_char(createddate, 'DD-MM-YYYY') <= :toDate order by createddate desc", nativeQuery = true)
    List<VehicleOwnerEntries> getReportAllParametersOwnerDates(@Param("districtId") Integer districtId,
                                                     @Param("barrierId") Integer barrierId,
                                                       @Param("ownerType") Integer ownerType,
                                                     @Param("fromDate") String fromDate,
                                                     @Param("toDate") String toDate);

    @Query(value = "select * from public.mst_vehicle_owner_entries where  district_id=:districtId AND barrier_id=:barrierId   AND active = true AND to_char(createddate, 'DD-MM-YYYY') >= :fromDate AND to_char(createddate, 'DD-MM-YYYY') <= :toDate order by createddate desc", nativeQuery = true)
    List<VehicleOwnerEntries> getReportDistrictBarrierFromTwo(@Param("districtId") Integer districtId,
                                                     @Param("barrierId") Integer barrierId,
                                                     @Param("fromDate") String fromDate,
                                                     @Param("toDate") String toDate);

    @Query(value = "select * from public.mst_vehicle_owner_entries where  district_id=:districtId AND barrier_id=:barrierId   AND active = true AND to_char(createddate, 'DD-MM-YYYY') >= :fromDate  order by createddate desc", nativeQuery = true)
    List<VehicleOwnerEntries> getReportDistrictBarrierFrom(@Param("districtId") Integer districtId,
                                                              @Param("barrierId") Integer barrierId,
                                                              @Param("fromDate") String fromDate);

    @Query(value = "select * from public.mst_vehicle_owner_entries where  district_id=:districtId AND barrier_id=:barrierId   AND active = true   order by createddate desc", nativeQuery = true)
    List<VehicleOwnerEntries> getReportDistrictBarrier(@Param("districtId") Integer districtId,
                                                           @Param("barrierId") Integer barrierId
                                                           );

    @Query(value = "select * from public.mst_vehicle_owner_entries where  district_id=:districtId AND barrier_id=:barrierId AND vehicle_type_id=:vehicleType  AND active = true  order by createddate desc", nativeQuery = true)
    List<VehicleOwnerEntries> getReportDistrictBarrierVehicleType(@Param("districtId") Integer districtId,
                                                 @Param("barrierId") Integer barrierId,
                                                 @Param("vehicleType") Integer vehicleType);


    @Query(value = "select * from public.mst_vehicle_owner_entries where  district_id=:districtId AND barrier_id=:barrierId AND vehicle_owner_type_id=:ownerType AND active = true order by createddate desc", nativeQuery = true)
    List<VehicleOwnerEntries> getReportDistrictBarrierOwnerTypeType(@Param("districtId") Integer districtId,
                                                 @Param("barrierId") Integer barrierId,
                                                 @Param("ownerType") Integer ownerType);


    @Query(value = "select * from public.mst_vehicle_owner_entries where  district_id=:districtId AND barrier_id=:barrierId AND vehicle_type_id=:vehicleType AND vehicle_owner_type_id=:ownerType AND active = true order by createddate desc", nativeQuery = true)
    List<VehicleOwnerEntries> getReportDistrictBarrierOwnerTypeVehicleType(@Param("districtId") Integer districtId,
                                                                    @Param("barrierId") Integer barrierId,
                                                                           @Param("vehicleType") Integer vehicleType,
                                                                    @Param("ownerType") Integer ownerType);

    @Query(value = "select * from public.mst_vehicle_owner_entries where  district_id=:districtId   AND active = true  order by createddate desc", nativeQuery = true)
    List<VehicleOwnerEntries> getReportDistrictType(@Param("districtId") Integer districtId);

}


