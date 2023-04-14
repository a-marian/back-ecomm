package com.back.ecomm.service;

import com.back.ecomm.mapper.CategoryMapper;
import com.back.ecomm.record.CategoryRecord;
import com.back.ecomm.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    CategoryService(@Autowired CategoryRepository categoryRepository,
                    @Autowired CategoryMapper categoryMapper){
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }


    public List<CategoryRecord> findAll(){
        var categories = categoryRepository.findAll();
        return categoryMapper.entityToRecord(categories);
    }
}
