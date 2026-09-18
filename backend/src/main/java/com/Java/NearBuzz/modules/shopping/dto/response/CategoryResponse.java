package com.Java.NearBuzz.modules.shopping.dto.response;

import com.Java.NearBuzz.modules.shopping.entity.ShoppingCategory;

public record CategoryResponse(Long id, String name, String slug, String icon) {
    public static CategoryResponse from(ShoppingCategory category) {
        return new CategoryResponse(category.getId(), category.getName(), category.getSlug(), category.getIcon());
    }
}
