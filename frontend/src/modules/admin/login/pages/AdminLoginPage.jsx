import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { ShieldCheck } from 'lucide-react';
import Card from '../../../../components/ui/Card';
import LoginForm from '../../../auth/components/LoginForm';
import { useAuth } from '../../../auth/hooks/useAuth';
import { ADMIN_ROLES } from '../../../../constants';

export default function AdminLoginPage() {
  const navigate = useNavigate();
  const { logout } = useAuth();
  const [notAuthorized, setNotAuthorized] = useState(false);

  function handleSuccess(user) {
    if (!ADMIN_ROLES.includes(user?.role)) {
      logout();
      setNotAuthorized(true);
      return;
    }
    navigate('/admin/dashboard');
  }

  return (
    <Card className="auth-card admin-login-card">
      <div className="admin-login-badge">
        <ShieldCheck size={16} aria-hidden="true" /> Admin access
      </div>
      <h1>NearBuzz Admin</h1>
      <p className="auth-subtitle">Sign in with your admin account</p>
      <LoginForm onSuccess={handleSuccess} />
      {notAuthorized && (
        <p className="form-error" style={{ marginTop: 12 }}>
          This account doesn't have admin access.
        </p>
      )}
    </Card>
  );
}
