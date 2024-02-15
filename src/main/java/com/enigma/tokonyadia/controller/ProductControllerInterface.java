package com.enigma.tokonyadia.controller;

import com.enigma.tokonyadia.entity.Product;
import com.enigma.tokonyadia.service.impl.ProductServiceImpl;
import com.enigma.tokonyadia.utils.constant.ApiUrlConstant;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ApiUrlConstant.PRODUCT)
public class ProductControllerInterface {

    private final ProductServiceImpl productServiceImpl;

    public ProductControllerInterface(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    @PostMapping
    public Product createNewProduct(@RequestBody Product product){
        return productServiceImpl.create(product);
    }

    @GetMapping
    public List<Product> getAllProduct(){
        return productServiceImpl.getAll();
    }

    @GetMapping("/{productsId}")
    public Optional<Product> getProductById(@PathVariable String productsId){
        return productServiceImpl.getById(productsId);
    }

    @PutMapping("/{productsId}")
    public Product updateById(@PathVariable String productsId,
                              @RequestBody Product product){
        product.setId(productsId);
        return productServiceImpl.update(product);
    }

    @DeleteMapping("/{productsId}")
    public void deleteById(@PathVariable String productsId){
        productServiceImpl.delete(productsId);
    }

    @GetMapping("/products-by-name")
    public List<Product> findByName(@RequestParam(name = "product-name",required = false) String name){
        return productServiceImpl.findProductByName(name);
    }
    @GetMapping("/products-by-name-like")
    public List<Product> findByNameContainingIgnoreCase(@RequestParam(name = "product-like",required = false) String name){
        return productServiceImpl.findByNameContainingIgnoreCase(name);
    }

    @GetMapping("/products-by-name-stock")
    public List<Product> findProductByNameOrStock(@RequestParam(name = "product-like-name",required = false) String name,
                                                  @RequestParam(name = "product-like-stock",required = false) Integer stock){
        return productServiceImpl.findProductByNameOrStock(name, stock);
    }

}
