package com.yijyun.phantommask.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MaskDto {
    Integer maskId;
    Integer pharmacyId;
    String name;
    Double price;
    Integer per;
}
