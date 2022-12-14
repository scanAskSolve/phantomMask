package com.yijyun.phantommask.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionMaskVo {
    String name;
    Integer totalMaskAmount;
    Double totalPriceAmount;
}
