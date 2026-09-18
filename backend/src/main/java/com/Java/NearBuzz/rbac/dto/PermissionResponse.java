package com.Java.NearBuzz.rbac.dto;

import com.Java.NearBuzz.rbac.entity.Permission;

public record PermissionResponse(Long id, String code, String module, String description) {
    public static PermissionResponse from(Permission permission) {
        return new PermissionResponse(
                permission.getId(), permission.getCode(), permission.getModule(), permission.getDescription());
    }
}
