import axiosClient from './axiosClient';

export function uploadImage(file, folder) {
  const formData = new FormData();
  formData.append('file', file);
  if (folder) formData.append('folder', folder);
  return axiosClient.post('/admin/media/upload', formData, {
    headers: { 'Content-Type': 'multipart/form-data' },
  });
}
