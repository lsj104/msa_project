package com.sparta.msa_exam.order.service;

import com.sparta.msa_exam.order.client.ProductClient;
import com.sparta.msa_exam.order.client.ProductResponseDto;
import com.sparta.msa_exam.order.dto.OrderRequestDto;
import com.sparta.msa_exam.order.dto.OrderResponseDto;
import com.sparta.msa_exam.order.entity.Order;
import com.sparta.msa_exam.order.entity.OrderItem;
import com.sparta.msa_exam.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;


    // 주문 추가
    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        List<OrderItem> orderItems = orderRequestDto.getOrderItemsList().stream()
                .map(productId -> {
                    ProductResponseDto product = productClient.getProduct(productId);
                    return OrderItem.builder()
                            .productId(product.getProduct_id())
                            .build();
                })
                .collect(Collectors.toList());

        Order order = Order.createOrder(orderRequestDto.getName(), orderItems);
        Order savedOrder = orderRepository.save(order);
        return toResponseDto(savedOrder);
    }



    @Transactional
    public OrderResponseDto updateOrder(Long orderId, List<Long> productIds) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "주문을 찾을 수 없습니다."));

        List<OrderItem> newOrderItems = productIds.stream()
                .map(productId -> {
                    ProductResponseDto product = productClient.getProduct(productId);
                    return OrderItem.builder()
                            .productId(product.getProduct_id())
                            .build();
                })
                .collect(Collectors.toList());

        // 기존 주문 항목에 새 항목 추가
        List<OrderItem> updatedOrderItems = order.getOrderItems();
        updatedOrderItems.addAll(newOrderItems);
        order.updateOrder(updatedOrderItems);

        Order updatedOrder = orderRepository.save(order);

        return toResponseDto(updatedOrder);
    }

    // 주문 조회 API결과를 캐싱 처리하여 60초동안 메모리에 캐싱된 데이터가 보여지도록 설정
    @Cacheable(cacheNames = "orderCache", key = "args[0]")
    @Transactional(readOnly = true)
    public OrderResponseDto getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "주문을 찾을 수 없습니다."));
        return toResponseDto(order);
    }


    private OrderResponseDto toResponseDto(Order order) {
        return new OrderResponseDto(
                order.getId(),
                order.getName(),
                order.getOrderItems().stream().map(OrderItem::getProductId).collect(Collectors.toList())
        );
    }
}

