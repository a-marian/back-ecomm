package com.back.ecomm.mapper;

import com.back.ecomm.entity.Category;
import com.back.ecomm.record.CategoryRecord;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryRecord entityToRecord(Category category);
    List<CategoryRecord> entityToRecord(Iterable<Category> categories);


}
