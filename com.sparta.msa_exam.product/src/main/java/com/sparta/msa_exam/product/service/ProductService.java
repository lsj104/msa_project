package com.sparta.msa_exam.product.service;

import com.sparta.msa_exam.product.dto.ProductRequestDto;
import com.sparta.msa_exam.product.dto.ProductResponseDto;
import com.sparta.msa_exam.product.dto.ProductSearchDto;
import com.sparta.msa_exam.product.entity.Product;
import com.sparta.msa_exam.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    // 상품 추가 API를 호출할 경우 상품 목록 API의 응답 데이터 캐시가 갱신됨

    // 상품 추가
    @CachePut(cacheNames = "productCache", key = "#result.product_id")
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        Product product = Product.createProduct(productRequestDto);
        Product saveProduct = productRepository.save(product);
        return toResponseDto(saveProduct);
    }

    // 상품 목록 조회
    @Cacheable(cacheNames = "productAllCache", key = "getMethodName()")
    public List<ProductResponseDto> getProducts(ProductSearchDto searchDto) {
        List<Product> products;
        if (searchDto.getName() != null && !searchDto.getName().isEmpty()) {
            products = productRepository.findByNameContaining(searchDto.getName());
        } else {
            products = productRepository.findAll();
        }
        return products.stream().map(this::toResponseDto).collect(Collectors.toList());
    }

    private ProductResponseDto toResponseDto(Product product) {
        return new ProductResponseDto(
                product.getProduct_id(),
                product.getName(),
                product.getSupply_price()
        );
    }


    @Transactional(readOnly = true)
    public ProductResponseDto getProductById(Long product_id) {
        Product product = productRepository.findById(product_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found or has been deleted"));
        return toResponseDto(product);
    }
}
