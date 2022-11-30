package com.yijyun.phantommask.dao;

import com.yijyun.phantommask.pojo.dto.MaskDto;
import com.yijyun.phantommask.pojo.dto.PharmaciesDto;
import com.yijyun.phantommask.pojo.dto.PharmacyOpeningHoursDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface IPharmaciesDao {
    @Insert("INSERT INTO `phantom_mask`.`pharmacy` (name,cash_balance) Values (#{bean.name},#{bean.cashBalance})")
    @Options(useGeneratedKeys=true, keyProperty="pharmacyId")
    void setPharmacy(@Param("bean") PharmaciesDto pharmaciesDto);

    @Insert("INSERT INTO `phantom_mask`.`pharmacy_opening_hours` (pharmacy_id,open_hours_start,open_hours_end,open_week) Values " +
            "(#{bean.pharmacyId},#{bean.openHoursStart},#{bean.openHoursEnd},#{bean.openWeek})")
    @Options(useGeneratedKeys=true, keyProperty="pharmacyOpeningHoursId")
    void setPharmacyOpeningHours(@Param("bean") PharmacyOpeningHoursDto pharmacyOpeningHoursDto);

    @Insert("INSERT INTO `phantom_mask`.`mask` (pharmacy_id,name,price) Values " +
            "(#{bean.pharmacyId},#{bean.name},#{bean.price})")
    @Options(useGeneratedKeys=true, keyProperty="maskId")
    void setMask(@Param("bean") MaskDto maskDto);
}
