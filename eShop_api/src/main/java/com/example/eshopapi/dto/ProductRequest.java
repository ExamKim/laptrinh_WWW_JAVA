package com.example.eshopapi.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank(message = "Tên sản phẩm không được để trống")
        @Size(max = 150)
        String name,

        @Size(max = 500)
        String description,

        @NotNull(message = "Giá không được để trống")
        @DecimalMin(value = "0", message = "Giá phải >= 0")
        BigDecimal price,

        @NotNull(message = "Số lượng không được để trống")
        @Min(value = 0, message = "Số lượng phải >= 0")
        Integer quantity,

        @Size(max = 500)
        String imageUrl,

        @NotNull(message = "Phải chọn danh mục")
        Long categoryId
) {}