import { Link } from 'react-router-dom';
import { MapPin } from 'lucide-react';
import { useAuth } from '../../modules/auth/hooks/useAuth';
import { ADMIN_ROLES } from '../../constants';
import Button from '../ui/Button';

export default function Navbar() {
  const { isAuthenticated, role, logout } = useAuth();
  const isAdmin = ADMIN_ROLES.includes(role);

  return (
    <header className="navbar">
      <Link to="/" className="navbar-brand">
        <MapPin size={20} aria-hidden="true" />
        NearBuzz
      </Link>
      <nav className="navbar-actions">
        <Link to="/shopping">Shop</Link>
        {isAuthenticated ? (
          <>
            <Link to={isAdmin ? '/admin/dashboard' : '/dashboard'}>
              {isAdmin ? 'Admin Panel' : 'Dashboard'}
            </Link>
            <Button variant="secondary" onClick={logout}>
              Log out
            </Button>
          </>
        ) : (
          <Link to="/login" className="btn btn-primary">
            Log in
          </Link>
        )}
      </nav>
    </header>
  );
}
