package com.yijyun.phantommask.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PharmaciesDto {
    Integer pharmaciesId;
    String name;
    Double cashBalance;
}
