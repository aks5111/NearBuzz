import { createContext, useCallback, useMemo, useState } from 'react';
import { loginRequest } from '../../../api/auth.api';
import {
  getToken,
  setToken,
  clearToken,
  getStoredUser,
  setStoredUser,
  clearStoredUser,
} from '../../../utils/storage';

export const AuthContext = createContext(null);

export function AuthProvider({ children }) {
  const [token, setTokenState] = useState(getToken());
  const [user, setUserState] = useState(getStoredUser());
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const login = useCallback(async (credentials) => {
    setLoading(true);
    setError(null);
    try {
      const { data } = await loginRequest(credentials);
      const payload = data?.data;
      const nextUser = {
        fullName: payload?.fullName,
        email: payload?.email,
        role: payload?.role,
      };
      setToken(payload?.token);
      setStoredUser(nextUser);
      setTokenState(payload?.token);
      setUserState(nextUser);
      return nextUser;
    } catch (err) {
      const message = err.response?.data?.message || 'Login failed. Check your credentials.';
      setError(message);
      throw new Error(message);
    } finally {
      setLoading(false);
    }
  }, []);

  const logout = useCallback(() => {
    clearToken();
    clearStoredUser();
    setTokenState(null);
    setUserState(null);
  }, []);

  const value = useMemo(
    () => ({
      token,
      user,
      role: user?.role ?? null,
      isAuthenticated: Boolean(token),
      loading,
      error,
      login,
      logout,
    }),
    [token, user, loading, error, login, logout]
  );

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}
