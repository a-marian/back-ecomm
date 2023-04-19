package com.back.ecomm.service;

import com.back.ecomm.record.ProductRecord;

import java.util.List;

public interface ProductService {

    List<ProductRecord> findByCategoryId(Long categoryId);

    List<ProductRecord> findAllProducts(Long categoryId, String productName);

    ProductRecord findProduct(String productId);
}
