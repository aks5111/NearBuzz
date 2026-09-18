package com.Java.NearBuzz.modules.shopping.controller;

import com.Java.NearBuzz.common.response.ApiResponse;
import com.Java.NearBuzz.modules.shopping.dto.response.CategoryResponse;
import com.Java.NearBuzz.modules.shopping.dto.response.ProductResponse;
import com.Java.NearBuzz.modules.shopping.service.ShoppingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shopping")
public class ShoppingController {

    private final ShoppingService shoppingService;

    public ShoppingController(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @GetMapping("/categories")
    public ApiResponse<List<CategoryResponse>> categories() {
        return ApiResponse.ok("Categories fetched", shoppingService.listCategories());
    }

    @GetMapping("/products")
    public ApiResponse<List<ProductResponse>> products(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String search) {
        return ApiResponse.ok("Products fetched", shoppingService.searchPublishedProducts(category, search));
    }
}
