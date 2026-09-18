import axiosClient from './axiosClient';

export function fetchRoles() {
  return axiosClient.get('/admin/roles');
}

export function fetchPermissions() {
  return axiosClient.get('/admin/permissions');
}

export function updateRolePermissions(roleId, permissionIds) {
  return axiosClient.put(`/admin/roles/${roleId}`, { permissionIds });
}
