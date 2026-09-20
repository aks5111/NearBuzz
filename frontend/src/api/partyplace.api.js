import axiosClient from './axiosClient';

export function fetchPartyPlace(search) {
  return axiosClient.get('/party-place', { params: { search: search || undefined } });
}

export function fetchAdminPartyPlace() {
  return axiosClient.get('/admin/party-place');
}

export function createAdminPartyPlace(payload) {
  return axiosClient.post('/admin/party-place', payload);
}

export function updateAdminPartyPlace(id, payload) {
  return axiosClient.put(`/admin/party-place/${id}`, payload);
}

export function deleteAdminPartyPlace(id) {
  return axiosClient.delete(`/admin/party-place/${id}`);
}
