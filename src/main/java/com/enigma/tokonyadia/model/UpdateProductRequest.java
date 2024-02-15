package com.enigma.tokonyadia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateProductRequest {

    @JsonIgnore
    @NotBlank
    @Size(max = 100)
    private String id;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotNull
    private Integer price;

    @NotNull
    private Integer stock;

}
