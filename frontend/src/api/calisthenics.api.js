import axiosClient from './axiosClient';

export function fetchAdminCalisthenics() {
  return axiosClient.get('/admin/calisthenics');
}

export function createAdminCalisthenics(payload) {
  return axiosClient.post('/admin/calisthenics', payload);
}

export function updateAdminCalisthenics(id, payload) {
  return axiosClient.put(`/admin/calisthenics/${id}`, payload);
}

export function deleteAdminCalisthenics(id) {
  return axiosClient.delete(`/admin/calisthenics/${id}`);
}
