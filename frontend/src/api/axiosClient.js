import axios from 'axios';
import { API_BASE_URL } from '../constants';
import { getToken, clearToken, clearStoredUser } from '../utils/storage';

const axiosClient = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

axiosClient.interceptors.request.use((config) => {
  const token = getToken();
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

axiosClient.interceptors.response.use(
  (response) => response,
  (error) => {
    const status = error.response?.status;
    // Spring Security's default entry point returns 403 (not 401) for a
    // missing/expired/invalid bearer token on a stateless API, so an
    // expired session shows up here as 403 — treat both as "not logged
    // in" and bounce back to login rather than a silent, confusing 403.
    if ((status === 401 || status === 403) && getToken()) {
      clearToken();
      clearStoredUser();
      const onAdminPage = window.location.pathname.startsWith('/admin');
      window.location.href = onAdminPage ? '/admin/login' : '/login';
    }
    return Promise.reject(error);
  }
);

export default axiosClient;
