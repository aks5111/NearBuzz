import { Navigate, Outlet } from 'react-router-dom';
import { useAuth } from '../modules/auth/hooks/useAuth';
import { ADMIN_ROLES } from '../constants';

export default function AdminRoute() {
  const { isAuthenticated, role } = useAuth();

  if (!isAuthenticated) {
    return <Navigate to="/admin/login" replace />;
  }

  if (!ADMIN_ROLES.includes(role)) {
    return <Navigate to="/" replace />;
  }

  return <Outlet />;
}
