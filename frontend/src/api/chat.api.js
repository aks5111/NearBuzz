import axiosClient from './axiosClient';

export function sendChatMessage(message, history) {
  return axiosClient.post('/chat', { message, history });
}
