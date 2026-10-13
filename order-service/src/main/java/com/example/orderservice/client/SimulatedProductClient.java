package com.example.orderservice.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/** Stand-in for a real HTTP call to Product Service: returns a fixed configurable price. */
@Component
public class SimulatedProductClient implements ProductClient {

    private final BigDecimal price;

    public SimulatedProductClient(@Value("${product-service.simulated-price}") BigDecimal price) {
        this.price = price;
    }

    @Override
    public BigDecimal getPrice(Long productId) {
        return price;
    }
}
