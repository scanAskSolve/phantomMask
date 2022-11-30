package com.yijyun.phantommask.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PurchaseHistoriesDto {
    Integer userId;
    String pharmacyName;
    String maskName;
    Double transactionAmount;
    String transactionDate;
}
