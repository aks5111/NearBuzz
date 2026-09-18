import axiosClient from './axiosClient';

export function fetchAdminSportsActivity() {
  return axiosClient.get('/admin/sports-activity');
}

export function createAdminSportsActivity(payload) {
  return axiosClient.post('/admin/sports-activity', payload);
}

export function updateAdminSportsActivity(id, payload) {
  return axiosClient.put(`/admin/sports-activity/${id}`, payload);
}

export function deleteAdminSportsActivity(id) {
  return axiosClient.delete(`/admin/sports-activity/${id}`);
}
