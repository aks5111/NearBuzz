import axiosClient from './axiosClient';

export function fetchMapPins() {
  return axiosClient.get('/map/pins');
}
