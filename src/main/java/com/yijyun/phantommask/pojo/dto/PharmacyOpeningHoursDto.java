package com.yijyun.phantommask.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PharmacyOpeningHoursDto {
    Integer pharmacyOpeningHoursId;
    Integer pharmacyId;
    String openHoursStart;
    String openHoursEnd;
    Integer openWeek;

}
