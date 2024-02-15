package com.enigma.tokonyadia.controller;

import com.enigma.tokonyadia.entity.Product;
import com.enigma.tokonyadia.model.CreateProductRequest;
import com.enigma.tokonyadia.model.ProductResponse;
import com.enigma.tokonyadia.model.UpdateProductRequest;
import com.enigma.tokonyadia.model.WebResponse;
import com.enigma.tokonyadia.repository.ProductRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
    }

    @Test
    void createSucces() throws Exception {
        CreateProductRequest request = new CreateProductRequest();
        request.setName("Oppo");
        request.setPrice(1_000_000);
        request.setStock(1);

        mockMvc.perform(
                post("/api/products")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertEquals("OK", response.getData());
        });
    }

    @Test
    void createBadRequest() throws Exception {
        CreateProductRequest request = new CreateProductRequest();
        request.setName("");
        request.setPrice(null);
        request.setStock(null);

        mockMvc.perform(
                post("/api/products")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNotNull(response.getErrors());
        });
    }

    @Test
    void getSucces() throws Exception {
        Product product = new Product();
        product.setId(UUID.randomUUID().toString());
        product.setName("Oppo");
        product.setPrice(1_000_000);
        product.setStock(1);

        productRepository.save(product);

        mockMvc.perform(
                get("/api/products/"+product.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<ProductResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNull(response.getErrors());
        });
    }

    @Test
    void getNotFound() throws Exception {
        mockMvc.perform(
                get("/api/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isNotFound()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNotNull(response.getErrors());
        });
    }

    @Test
    void updateSucces() throws Exception {
        Product product = new Product();
        product.setId(UUID.randomUUID().toString());
        product.setName("Oppo");
        product.setPrice(1_000_000);
        product.setStock(1);
        productRepository.save(product);

        UpdateProductRequest request = new UpdateProductRequest();
        request.setName("Samsung");
        request.setPrice(1_000_000);
        request.setStock(2);

        mockMvc.perform(
                put("/api/products/" + product.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<ProductResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNull(response.getErrors());
        });
    }

    @Test
    void updateBadRequest() throws Exception {
        UpdateProductRequest request = new UpdateProductRequest();
        request.setName("");
        request.setPrice(null);
        request.setStock(null);

        mockMvc.perform(
                put("/api/products/123")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNotNull(response.getErrors());
        });
    }

    @Test
    void deleteProductsNotFound() throws Exception {
        mockMvc.perform(
                delete("/api/products/12123131")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isNotFound()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<String>>() {
            });
            assertNotNull(response.getErrors());
        });
    }

    @Test
    void deleteSucces() throws Exception {
        Product product = new Product();
        product.setId(UUID.randomUUID().toString());
        product.setName("Oppo");
        product.setPrice(1_000_000);
        product.setStock(1);
        productRepository.save(product);

        mockMvc.perform(
                delete("/api/products/" + product.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNull(response.getErrors());
        });
    }

}