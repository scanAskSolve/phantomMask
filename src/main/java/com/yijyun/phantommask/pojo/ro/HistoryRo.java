package com.yijyun.phantommask.pojo.ro;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class HistoryRo {

    @JsonProperty("name")
    private String name;
    @JsonProperty("cashBalance")
    private Double cashBalance;
    @JsonProperty("purchaseHistories")
    private List<PurchaseHistoriesDTO> purchaseHistories;

    @NoArgsConstructor
    @Data
    public static class PurchaseHistoriesDTO {
        @JsonProperty("pharmacyName")
        private String pharmacyName;
        @JsonProperty("maskName")
        private String maskName;
        @JsonProperty("transactionAmount")
        private Double transactionAmount;
        @JsonProperty("transactionDate")
        private String transactionDate;
    }
}
