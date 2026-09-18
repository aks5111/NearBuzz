package com.Java.NearBuzz.rbac.service;

import com.Java.NearBuzz.common.exception.BadRequestException;
import com.Java.NearBuzz.rbac.dto.PermissionResponse;
import com.Java.NearBuzz.rbac.dto.RoleResponse;
import com.Java.NearBuzz.rbac.entity.Permission;
import com.Java.NearBuzz.rbac.entity.RoleDefinition;
import com.Java.NearBuzz.rbac.repository.PermissionRepository;
import com.Java.NearBuzz.rbac.repository.RoleDefinitionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleService {

    private final RoleDefinitionRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public RoleService(RoleDefinitionRepository roleRepository, PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    public List<RoleResponse> listRoles() {
        return roleRepository.findAll().stream().map(RoleResponse::from).toList();
    }

    public List<PermissionResponse> listPermissions() {
        return permissionRepository.findAll().stream().map(PermissionResponse::from).toList();
    }

    @Transactional
    public RoleResponse updatePermissions(Long roleId, List<Long> permissionIds) {
        RoleDefinition role = roleRepository.findById(roleId)
                .orElseThrow(() -> new BadRequestException("Role not found"));

        if ("ROLE_SUPER_ADMIN".equals(role.getName())) {
            throw new BadRequestException("Super admin permissions can't be changed");
        }

        Set<Permission> permissions = new HashSet<>(permissionRepository.findAllById(permissionIds));
        role.setPermissions(permissions);
        return RoleResponse.from(role);
    }
}
