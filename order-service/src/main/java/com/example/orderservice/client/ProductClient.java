package com.example.orderservice.client;

import java.math.BigDecimal;

/** Port to Product Service. Order Service only knows productId and asks for the price through this. */
public interface ProductClient {
    BigDecimal getPrice(Long productId);
}
