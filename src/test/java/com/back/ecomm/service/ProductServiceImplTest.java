package com.back.ecomm.service;

import com.back.ecomm.entity.Product;
import com.back.ecomm.mapper.ProductMapper;
import com.back.ecomm.record.ProductRecord;
import com.back.ecomm.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

        @Mock
        private ProductMapper productMapper;

        @Mock
        private ProductRepository productRepository;

        @InjectMocks
        private ProductServiceImpl productService;

        @Test
        void findByCategoryId_shouldReturnProductRecords() {
            // Arrange
            Long categoryId = 1L;
            List<Product> mockProducts = Arrays.asList(new Product(), new Product());
            List<ProductRecord> expectedProductRecords = getProducts();

            when(productRepository.findByCategoryCategoryId(categoryId)).thenReturn(mockProducts);
            when(productMapper.entityToRecord(mockProducts)).thenReturn(expectedProductRecords);

            // Act
            List<ProductRecord> result = productService.findByCategoryId(categoryId);

            // Assert
            assertEquals(expectedProductRecords, result);
            verify(productRepository, times(1)).findByCategoryCategoryId(categoryId);
            verify(productMapper, times(1)).entityToRecord(mockProducts);
        }

        @Test
        void findAllProducts_shouldReturnProductRecords() {
            // Arrange
            Long categoryId = 1L;
            String productName = "TestProduct";
            List<Product> mockProducts = Arrays.asList(new Product(), new Product());
            List<ProductRecord> expectedProductRecords = getProducts();

            when(productRepository.findByProductNameLike(productName)).thenReturn(mockProducts);
            when(productRepository.findByCategoryCategoryId(categoryId)).thenReturn(mockProducts);
            when(productMapper.entityToRecord(mockProducts)).thenReturn(expectedProductRecords);

            // Act
            List<ProductRecord> resultWithCategoryId = productService.findAllProducts(categoryId, null);
            List<ProductRecord> resultWithProductName = productService.findAllProducts(null, productName);

            // Assert
            assertEquals(expectedProductRecords, resultWithCategoryId);
            assertEquals(expectedProductRecords, resultWithProductName);

            verify(productRepository, times(1)).findByProductNameLike(productName);
            verify(productRepository, times(1)).findByCategoryCategoryId(categoryId);
            verify(productMapper, times(2)).entityToRecord(mockProducts);
        }

        @Test
        void findProduct_shouldReturnProductRecord() {
            // Arrange
            String productId = "1";
            Product mockProduct = new Product();
            ProductRecord expectedProductRecord = new ProductRecord(100, "QWER10", "Barbie Weird",
                    "dolls", BigDecimal.valueOf(1400), "/toy/picture3", true, 100 );

            when(productRepository.findById(any())).thenReturn(Optional.of(mockProduct));
            when(productMapper.entityToRecord(mockProduct)).thenReturn(expectedProductRecord);

            // Act
            ProductRecord result = productService.findProduct(productId);

            // Assert
            assertEquals(expectedProductRecord, result);
            verify(productRepository, times(1)).findById(any());
            verify(productMapper, times(1)).entityToRecord(mockProduct);
        }

        @Test
        void findProduct_shouldThrowExceptionWhenProductNotFound() {
            // Arrange
            String productId = "1";

            when(productRepository.findById(any())).thenReturn(Optional.empty());

            // Act and Assert
            assertThrows(IllegalArgumentException.class, () -> productService.findProduct(productId));

            verify(productRepository, times(1)).findById(any());
            verify(productMapper, never()).entityToRecord( any(Product.class));
        }

        private List<ProductRecord> getProducts(){
           return Arrays.asList(
                    new ProductRecord(10, "ASDF10", "optimus prime", "transformer", BigDecimal.valueOf(1400),
                            "/toy/picture1", true, 120 ),
                    new ProductRecord(11, "ASDF11", "robocop", "robots", BigDecimal.valueOf(1450),
                            "/toy/picture2", true, 100 ));
        }
    }