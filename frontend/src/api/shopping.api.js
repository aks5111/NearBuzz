import axiosClient from './axiosClient';

export function fetchShoppingCategories() {
  return axiosClient.get('/shopping/categories');
}

export function fetchShoppingProducts({ category, search } = {}) {
  return axiosClient.get('/shopping/products', { params: { category, search } });
}

export function fetchAdminProducts() {
  return axiosClient.get('/admin/shopping/products');
}

export function createAdminProduct(payload) {
  return axiosClient.post('/admin/shopping/products', payload);
}

export function updateAdminProduct(id, payload) {
  return axiosClient.put(`/admin/shopping/products/${id}`, payload);
}

export function deleteAdminProduct(id) {
  return axiosClient.delete(`/admin/shopping/products/${id}`);
}
