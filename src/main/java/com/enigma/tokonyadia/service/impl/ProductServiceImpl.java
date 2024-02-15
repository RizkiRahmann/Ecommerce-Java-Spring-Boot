package com.enigma.tokonyadia.service.impl;

import com.enigma.tokonyadia.entity.Product;
import com.enigma.tokonyadia.repository.ProductRepositoryInterface;
import com.enigma.tokonyadia.service.ProductServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductServiceInterface {

    private final ProductRepositoryInterface productRepositoryInterface;

//    @Autowired
    public ProductServiceImpl(ProductRepositoryInterface productRepositoryInterface) {
        this.productRepositoryInterface = productRepositoryInterface;
    }

    @Override
    public Product create(Product product) {
        return productRepositoryInterface.save(product);
    }

    @Override
    public List<Product> getAll() {
        return productRepositoryInterface.findAll();
    }

    @Override
    public Optional<Product> getById(String id) {
        return productRepositoryInterface.findById(id);
    }

    @Override
    public Product update(Product product) {
        return productRepositoryInterface.save(product);
    }

    @Override
    public void delete(String id) {
//        validateId(id);
//
//        productRepositoryInterface.delete();
        productRepositoryInterface.deleteById(id);
    }

//    private void validateId(String id) {
//        Optional<Product> optionalProduct = productRepositoryInterface.findById(id);
//        if (optionalProduct.isEmpty()) throw  new ResponseStatusException(HttpStatus.NOT_FOUND,"product is not found");
//        return optionalProduct;
//    }

    @Override
    public List<Product> findProductByName(String name) {
        return productRepositoryInterface.findProductByName(name);
    }

    @Override
    public List<Product> findByNameContainingIgnoreCase(String name) {
        return productRepositoryInterface.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Product> findProductByNameOrStock(String name, Integer stock) {
        return productRepositoryInterface.findProductByNameOrStock(name, stock);
    }


}
