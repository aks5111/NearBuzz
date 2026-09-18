export const API_BASE_URL = import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080/api/v1';

export const AUTH_TOKEN_KEY = 'nearbuzz_auth_token';
export const AUTH_USER_KEY = 'nearbuzz_auth_user';

export const ROLES = {
  USER: 'ROLE_USER',
  ADMIN: 'ROLE_ADMIN',
  SUPER_ADMIN: 'ROLE_SUPER_ADMIN',
};

export const ADMIN_ROLES = [ROLES.ADMIN, ROLES.SUPER_ADMIN];
