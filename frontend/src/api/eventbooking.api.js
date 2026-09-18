import axiosClient from './axiosClient';

export function fetchAdminEventBooking() {
  return axiosClient.get('/admin/event-booking');
}

export function createAdminEventBooking(payload) {
  return axiosClient.post('/admin/event-booking', payload);
}

export function updateAdminEventBooking(id, payload) {
  return axiosClient.put(`/admin/event-booking/${id}`, payload);
}

export function deleteAdminEventBooking(id) {
  return axiosClient.delete(`/admin/event-booking/${id}`);
}
