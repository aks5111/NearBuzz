import axiosClient from './axiosClient';

export function loginRequest({ email, password }) {
  return axiosClient.post('/auth/login', { email, password });
}

export function registerRequest(payload) {
  return axiosClient.post('/auth/register', payload);
}

export function fetchCurrentUser() {
  return axiosClient.get('/auth/me');
}
