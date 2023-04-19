package com.back.ecomm.repository;

import com.back.ecomm.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    @Query("SELECT p from Product p where p.productId = :productId")
    Optional<Product> findById(Long productId);

    List<Product> findByCategoryCategoryId(Long categoryId);


    @Query("SELECT p FROM Product p WHERE p.productName LIKE %:productName%")
    List<Product> findByProductNameLike(@Param("productName")String productName);

}
