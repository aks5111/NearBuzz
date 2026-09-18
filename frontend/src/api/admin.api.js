import axiosClient from './axiosClient';

export function fetchAdminUsers() {
  return axiosClient.get('/admin/users');
}
