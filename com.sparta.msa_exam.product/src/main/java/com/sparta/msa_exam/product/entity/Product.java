package com.sparta.msa_exam.product.entity;

import com.sparta.msa_exam.product.dto.ProductRequestDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PRIVATE)
@Table(name = "products")
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;

    private String name;
    private Integer supply_price;


    public static Product createProduct(ProductRequestDto productRequestDto) {
        return Product.builder()
                .name(productRequestDto.getName())
                .supply_price(productRequestDto.getSupply_price())
                .build();
    }
}
