package com.back.ecomm.service;

import com.back.ecomm.record.ProductRecord;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    List<ProductRecord> findByCategoryId(Long categoryId, Pageable pageable);

    List<ProductRecord> findAllProducts(Long categoryId, String productName, Pageable pageable);

    ProductRecord findProduct(String productId);
}
