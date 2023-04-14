package com.back.ecomm.service;

import com.back.ecomm.entity.Product;
import com.back.ecomm.mapper.ProductMapper;
import com.back.ecomm.record.ProductRecord;
import com.back.ecomm.repository.ProductRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    ProductServiceImpl(@Autowired ProductMapper mapper,
                       @Autowired  ProductRepository productRepository){
        this.productMapper = mapper;
        this.productRepository = productRepository;
    }


    @Override
    public List<ProductRecord> findByCategoryId(@NonNull Long categoryId, Pageable pageable) {
        List<Product> page = productRepository.findByCategoryCategoryId(categoryId, pageable );
        List<Product> products = page.stream().toList();
        return productMapper.entityToRecord(products);
    }

    @Override
    public List<ProductRecord> findAllProducts(Long categoryId, String productName, Pageable pageable) {
        List<Product> products;
        if (categoryId == null){
            products = productRepository.findByProductNameContaining(productName, pageable);
        }else {
            products = productRepository.findByCategoryCategoryId(categoryId, pageable);
        }
        return productMapper.entityToRecord(products);
    }
}
