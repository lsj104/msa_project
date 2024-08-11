package com.sparta.msa_exam.order.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {
    @JsonProperty("product_id")
    private Long product_id;
    private String name;
    private Integer supply_price;
}
