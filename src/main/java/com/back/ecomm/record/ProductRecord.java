package com.back.ecomm.record;

import java.math.BigDecimal;

public record ProductRecord(
        String sku,
        String productName,
        String description,
        BigDecimal unitPrice,
        String imageUrl,
        boolean active,
        Integer unitsInstock

) { }
