package com.Java.NearBuzz.rbac.controller;

import com.Java.NearBuzz.common.response.ApiResponse;
import com.Java.NearBuzz.rbac.dto.PermissionResponse;
import com.Java.NearBuzz.rbac.dto.RoleResponse;
import com.Java.NearBuzz.rbac.dto.UpdateRolePermissionsRequest;
import com.Java.NearBuzz.rbac.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminRoleController {

    private final RoleService roleService;

    public AdminRoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/roles")
    public ApiResponse<List<RoleResponse>> roles() {
        return ApiResponse.ok("Roles fetched", roleService.listRoles());
    }

    @GetMapping("/permissions")
    public ApiResponse<List<PermissionResponse>> permissions() {
        return ApiResponse.ok("Permissions fetched", roleService.listPermissions());
    }

    @PutMapping("/roles/{id}")
    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    public ApiResponse<RoleResponse> updateRole(
            @PathVariable Long id, @Valid @RequestBody UpdateRolePermissionsRequest request) {
        return ApiResponse.ok("Role updated", roleService.updatePermissions(id, request.permissionIds()));
    }
}
