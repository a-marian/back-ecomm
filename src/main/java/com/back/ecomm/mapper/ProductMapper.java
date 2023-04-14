package com.back.ecomm.mapper;

import com.back.ecomm.entity.Product;
import com.back.ecomm.record.ProductRecord;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductRecord entityToRecord(Product product);
    Product recordToEntity(ProductRecord productRecord);
    List<Product> recordToEntity(Iterable<ProductRecord> productRecords);

    List<ProductRecord> entityToRecord(List<Product> product);

}
