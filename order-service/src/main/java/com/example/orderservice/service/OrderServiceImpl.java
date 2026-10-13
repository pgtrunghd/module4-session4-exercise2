package com.example.orderservice.service;

import com.example.orderservice.client.ProductClient;
import com.example.orderservice.dto.OrderRequestDTO;
import com.example.orderservice.dto.OrderResponseDTO;
import com.example.orderservice.entity.Order;
import com.example.orderservice.exception.ResourceNotFoundException;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    @Override
    public OrderResponseDTO create(OrderRequestDTO request) {
        BigDecimal unitPrice = productClient.getPrice(request.getProductId());
        BigDecimal total = unitPrice.multiply(BigDecimal.valueOf(request.getQuantity()));

        Order order = new Order();
        order.setCustomerId(request.getCustomerId());
        order.setProductId(request.getProductId());
        order.setOrderDate(LocalDateTime.now());
        order.setTotalAmount(total);
        return toResponse(orderRepository.save(order));
    }

    @Override
    public OrderResponseDTO getById(Long id) {
        return orderRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Đơn hàng với ID " + id + " không tồn tại!"));
    }

    private OrderResponseDTO toResponse(Order o) {
        return new OrderResponseDTO(o.getId(), o.getCustomerId(), o.getProductId(),
                o.getOrderDate(), o.getTotalAmount());
    }
}
