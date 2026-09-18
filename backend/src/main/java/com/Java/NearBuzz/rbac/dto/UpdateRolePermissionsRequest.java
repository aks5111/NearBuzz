package com.Java.NearBuzz.rbac.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UpdateRolePermissionsRequest(@NotNull List<Long> permissionIds) {
}
