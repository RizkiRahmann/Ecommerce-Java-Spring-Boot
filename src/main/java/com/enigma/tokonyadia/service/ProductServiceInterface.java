package com.enigma.tokonyadia.service;

import com.enigma.tokonyadia.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductServiceInterface {
    Product create(Product product);
    List<Product> getAll();
    Optional<Product> getById(String id);
    Product update(Product product);
    void delete(String id);

    List<Product> findProductByName(String name);
    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findProductByNameOrStock(String name,Integer stock);
}
