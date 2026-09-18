import axiosClient from './axiosClient';

export function fetchAdminTravel() {
  return axiosClient.get('/admin/travel');
}

export function createAdminTravel(payload) {
  return axiosClient.post('/admin/travel', payload);
}

export function updateAdminTravel(id, payload) {
  return axiosClient.put(`/admin/travel/${id}`, payload);
}

export function deleteAdminTravel(id) {
  return axiosClient.delete(`/admin/travel/${id}`);
}
