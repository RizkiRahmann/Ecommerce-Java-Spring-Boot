package com.enigma.tokonyadia.service;

import com.enigma.tokonyadia.entity.Product;
import com.enigma.tokonyadia.model.CreateProductRequest;
import com.enigma.tokonyadia.model.ProductResponse;
import com.enigma.tokonyadia.model.UpdateProductRequest;
import com.enigma.tokonyadia.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ValidationService validationService;

    private ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();
    }

    @Transactional
    public ProductResponse create(CreateProductRequest request){
        validationService.validate(request);

        Product product = new Product();
        product.setId(UUID.randomUUID().toString());
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());

        productRepository.save(product);

        return toProductResponse(product);
    }

    @Transactional
    public ProductResponse get(String id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found"));

        return toProductResponse(product);
    }

    @Transactional
    public ProductResponse update(UpdateProductRequest request){
        validationService.validate(request);

        Product product = productRepository.findById(request.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found"));

        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());

        productRepository.save(product);

        return toProductResponse(product);
    }

    @Transactional
    public void delete(String id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found"));

        productRepository.delete(product);
    }

}
