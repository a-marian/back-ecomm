package com.back.ecomm.repository;

import com.back.ecomm.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findByCategoryCategoryId(@RequestParam("categoryId") Long categoryId, Pageable pageable);

    List<Product> findByProductNameContaining(@RequestParam("name") String name, Pageable pageable);

}
