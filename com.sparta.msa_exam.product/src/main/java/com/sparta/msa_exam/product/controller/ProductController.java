package com.sparta.msa_exam.product.controller;

import com.sparta.msa_exam.product.dto.ProductRequestDto;
import com.sparta.msa_exam.product.dto.ProductResponseDto;
import com.sparta.msa_exam.product.dto.ProductSearchDto;
import com.sparta.msa_exam.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @Value("${server.port}")
    private String serverPort;


    // 상품 추가
    @PostMapping
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto productRequestDto) {
        return productService.createProduct(productRequestDto);
    }

    // 상품 목록 조회
    @GetMapping
    public List<ProductResponseDto> getProducts(ProductSearchDto searchDto) {
        return productService.getProducts(searchDto);
    }

    @GetMapping("/{productId}")
    public ProductResponseDto getProductById(@PathVariable("productId") Long productId) {
        return productService.getProductById(productId);
    }




}
