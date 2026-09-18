package com.Java.NearBuzz.modules.shopping.repository;

import com.Java.NearBuzz.modules.shopping.entity.ProductStatus;
import com.Java.NearBuzz.modules.shopping.entity.ShoppingProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingProductRepository extends JpaRepository<ShoppingProduct, Long> {
    List<ShoppingProduct> findByStatusOrderByCreatedAtDesc(ProductStatus status);
}
