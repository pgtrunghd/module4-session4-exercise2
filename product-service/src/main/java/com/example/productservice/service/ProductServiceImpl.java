package com.example.productservice.service;

import com.example.productservice.dto.ProductRequestDTO;
import com.example.productservice.dto.ProductResponseDTO;
import com.example.productservice.entity.Product;
import com.example.productservice.exception.ResourceNotFoundException;
import com.example.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponseDTO create(ProductRequestDTO request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setStockQuantity(request.getStockQuantity());
        return toResponse(productRepository.save(product));
    }

    @Override
    public ProductResponseDTO getById(Long id) {
        return productRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Sản phẩm với ID " + id + " không tồn tại!"));
    }

    @Override
    public List<ProductResponseDTO> getAll() {
        return productRepository.findAll().stream().map(this::toResponse).toList();
    }

    private ProductResponseDTO toResponse(Product p) {
        return new ProductResponseDTO(p.getId(), p.getName(), p.getPrice(), p.getStockQuantity());
    }
}
