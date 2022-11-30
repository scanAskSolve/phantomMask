package com.yijyun.phantommask.pojo.ro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionRo {
    String name;
    String pharmacyName;
    String maskName;
    Double transactionAmount;

}
