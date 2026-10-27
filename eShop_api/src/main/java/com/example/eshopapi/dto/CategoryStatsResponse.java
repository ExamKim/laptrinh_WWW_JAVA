package com.example.eshopapi.dto;

import java.math.BigDecimal;

public record CategoryStatsResponse(
        Long categoryId,
        String categoryName,
        Long productCount,
        BigDecimal totalStockValue
) {}
