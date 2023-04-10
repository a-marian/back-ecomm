package com.back.ecomm.repository;

import com.back.ecomm.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByCategoryCategoryId(@RequestParam("id") Long id, Pageable pageable);

    Page<Product> findByProductNameContaining(@RequestParam("name") String name, Pageable pageable);

}
