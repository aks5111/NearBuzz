package com.Java.NearBuzz.modules.shopping.controller;

import com.Java.NearBuzz.common.response.ApiResponse;
import com.Java.NearBuzz.modules.shopping.dto.request.ProductRequest;
import com.Java.NearBuzz.modules.shopping.dto.response.ProductResponse;
import com.Java.NearBuzz.modules.shopping.service.ShoppingService;
import com.Java.NearBuzz.user.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/shopping/products")
public class AdminShoppingController {

    private final ShoppingService shoppingService;
    private final UserRepository userRepository;

    public AdminShoppingController(ShoppingService shoppingService, UserRepository userRepository) {
        this.shoppingService = shoppingService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ApiResponse<List<ProductResponse>> list() {
        return ApiResponse.ok("Products fetched", shoppingService.listAllProducts());
    }

    @PostMapping
    public ApiResponse<ProductResponse> create(
            @Valid @RequestBody ProductRequest request, @AuthenticationPrincipal UserDetails principal) {
        Long userId = userRepository.findByEmail(principal.getUsername())
                .map(u -> u.getId())
                .orElse(null);
        return ApiResponse.ok("Product created", shoppingService.createProduct(request, userId));
    }

    @PutMapping("/{id}")
    public ApiResponse<ProductResponse> update(@PathVariable Long id, @Valid @RequestBody ProductRequest request) {
        return ApiResponse.ok("Product updated", shoppingService.updateProduct(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        shoppingService.deleteProduct(id);
        return ApiResponse.ok("Product deleted", null);
    }
}
