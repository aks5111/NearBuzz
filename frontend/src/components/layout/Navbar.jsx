import { Link } from 'react-router-dom';
import { MapPin } from 'lucide-react';
import { useAuth } from '../../modules/auth/hooks/useAuth';
import { ADMIN_ROLES } from '../../constants';

export default function Navbar() {
  const { isAuthenticated, role } = useAuth();
  const isAdmin = ADMIN_ROLES.includes(role);

  return (
    <header className="navbar">
      <Link to="/" className="navbar-brand">
        <MapPin size={20} aria-hidden="true" />
        NearBuzz
      </Link>
      <nav className="navbar-actions">
        <Link to="/">Home</Link>
        <Link to="/map">Map</Link>
        {isAuthenticated ? (
          <Link to={isAdmin ? '/admin/dashboard' : '/dashboard'}>
            {isAdmin ? 'Admin Panel' : 'Dashboard'}
          </Link>
        ) : (
          <Link to="/login" className="btn btn-primary">
            Log in
          </Link>
        )}
      </nav>
    </header>
  );
}
