package com.Java.NearBuzz.modules.shopping.repository;

import com.Java.NearBuzz.modules.shopping.entity.ShoppingCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCategoryRepository extends JpaRepository<ShoppingCategory, Long> {
}
