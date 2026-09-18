import axiosClient from './axiosClient';

export function searchPlaces(query) {
  return axiosClient.get('/geo/places/search', { params: { q: query } });
}
