package com.back.ecomm.service;

import com.back.ecomm.entity.Product;
import com.back.ecomm.mapper.ProductMapper;
import com.back.ecomm.record.ProductRecord;
import com.back.ecomm.repository.ProductRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<ProductRecord> findByCategoryId(@NonNull Long categoryId) {
        List<Product> page = productRepository.findByCategoryCategoryId(categoryId);
        List<Product> products = page.stream().toList();
        return productMapper.entityToRecord(products);
    }

    @Override
    public List<ProductRecord> findAllProducts(Long categoryId, String productName) {
        List<Product> products;
        if (categoryId == null){
            products = productRepository.findByProductNameLike(productName);
        }else {
            products = productRepository.findByCategoryCategoryId(categoryId);
        }
        return productMapper.entityToRecord(products);
    }

    @Override
    public ProductRecord findProduct(String productId) {
        var product = productRepository.findById(Long.valueOf(productId));
        if (product.isPresent()){
            return productMapper.entityToRecord(product.get());
        }else{
            return null;
        }
    }
}
