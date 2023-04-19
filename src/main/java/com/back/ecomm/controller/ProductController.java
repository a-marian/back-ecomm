package com.back.ecomm.controller;


import com.back.ecomm.record.ProductRecord;
import com.back.ecomm.service.ProductService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class ProductController {

    private final ProductService productService;


    ProductController(@Autowired ProductService productService){
        this.productService = productService;
    }

    @GetMapping("products")
    ResponseEntity<List<ProductRecord>> findAllProducts(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String productName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ){
        Pageable pageable = PageRequest.of(page, size);
      List<ProductRecord> productRecords = productService.findAllProducts(categoryId, productName);
        return new ResponseEntity<>(productRecords, HttpStatus.OK);
    }

    @GetMapping("product/{productId}")
    ResponseEntity<ProductRecord> findProduct(@NonNull @PathVariable String productId){

        ProductRecord productRecord = productService.findProduct(productId);
        return new ResponseEntity<>(productRecord, HttpStatus.OK );
    }

}
