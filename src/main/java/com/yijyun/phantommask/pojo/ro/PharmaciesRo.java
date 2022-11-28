package com.yijyun.phantommask.pojo.ro;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class PharmaciesRo {

    @JsonProperty("name")
    private String name;
    @JsonProperty("cashBalance")
    private Double cashBalance;
    @JsonProperty("openingHours")
    private String openingHours;
    @JsonProperty("masks")
    private List<MasksDTO> masks;

    @NoArgsConstructor
    @Data
    public static class MasksDTO {
        @JsonProperty("name")
        private String name;
        @JsonProperty("price")
        private Double price;
    }
}
