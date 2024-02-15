package com.enigma.tokonyadia.controller;

import com.enigma.tokonyadia.model.CreateProductRequest;
import com.enigma.tokonyadia.model.ProductResponse;
import com.enigma.tokonyadia.model.UpdateProductRequest;
import com.enigma.tokonyadia.model.WebResponse;
import com.enigma.tokonyadia.service.ProductService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(
            path = "/api/products",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> create(@RequestBody CreateProductRequest request){
        productService.create(request);
        return WebResponse.<String>builder().data("OK").build();
    }

    @GetMapping(
            path = "/api/products/{productId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ProductResponse> get(@PathVariable("productId") String productId){
        ProductResponse productResponse = productService.get(productId);
        return WebResponse.<ProductResponse>builder().data(productResponse).build();
    }

    @PutMapping(
            path = "/api/products/{productId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ProductResponse> update(@PathVariable("productId") String productId,
                                               @RequestBody UpdateProductRequest request){
        request.setId(productId);
        ProductResponse productResponse = productService.update(request);
        return WebResponse.<ProductResponse>builder().data(productResponse).build();
    }

    @DeleteMapping(
            path = "/api/products/{productId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(@PathVariable("productId") String productId){
        productService.delete(productId);
        return WebResponse.<String>builder().data("OK").build();
    }

}
