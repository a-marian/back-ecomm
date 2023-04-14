package com.back.ecomm.controller;

import com.back.ecomm.record.CategoryRecord;
import com.back.ecomm.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class CategoryController {

    private final CategoryService categoryService;

    CategoryController(@Autowired CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    ResponseEntity<List<CategoryRecord>> getAllCategories(){
        List<CategoryRecord> categoryRecords = categoryService.findAll();
        return new ResponseEntity<>(categoryRecords, HttpStatus.OK);
    }
}
