package com.Java.NearBuzz.rbac.dto;

import com.Java.NearBuzz.rbac.entity.RoleDefinition;

import java.util.List;

public record RoleResponse(Long id, String name, String description, List<Long> permissionIds) {
    public static RoleResponse from(RoleDefinition role) {
        List<Long> ids = role.getPermissions().stream().map(p -> p.getId()).sorted().toList();
        return new RoleResponse(role.getId(), role.getName(), role.getDescription(), ids);
    }
}
