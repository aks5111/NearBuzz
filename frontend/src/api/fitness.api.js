import axiosClient from './axiosClient';

export function fetchAdminFitness() {
  return axiosClient.get('/admin/fitness');
}

export function createAdminFitness(payload) {
  return axiosClient.post('/admin/fitness', payload);
}

export function updateAdminFitness(id, payload) {
  return axiosClient.put(`/admin/fitness/${id}`, payload);
}

export function deleteAdminFitness(id) {
  return axiosClient.delete(`/admin/fitness/${id}`);
}
