import axiosClient from './axiosClient';

export function fetchFriendsNearby(search) {
  return axiosClient.get('/friends-nearby', { params: { search: search || undefined } });
}

export function fetchAdminFriendsNearby() {
  return axiosClient.get('/admin/friends-nearby');
}

export function createAdminFriendsNearby(payload) {
  return axiosClient.post('/admin/friends-nearby', payload);
}

export function updateAdminFriendsNearby(id, payload) {
  return axiosClient.put(`/admin/friends-nearby/${id}`, payload);
}

export function deleteAdminFriendsNearby(id) {
  return axiosClient.delete(`/admin/friends-nearby/${id}`);
}
