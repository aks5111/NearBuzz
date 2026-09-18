package com.Java.NearBuzz.rbac.repository;

import com.Java.NearBuzz.rbac.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
