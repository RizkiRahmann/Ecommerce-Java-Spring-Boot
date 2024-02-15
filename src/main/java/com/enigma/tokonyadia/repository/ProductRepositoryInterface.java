package com.enigma.tokonyadia.repository;

import com.enigma.tokonyadia.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepositoryInterface extends JpaRepository<Product, String> {

    List<Product> findProductByName(String name);

    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findProductByNameOrStock(String name, Integer stock);
}
