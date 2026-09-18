import { Link } from 'react-router-dom';
import { MapPin } from 'lucide-react';
import { useAuth } from '../../modules/auth/hooks/useAuth';
import Button from '../ui/Button';

export default function Navbar() {
  const { isAuthenticated, logout } = useAuth();

  return (
    <header className="navbar">
      <Link to="/" className="navbar-brand">
        <MapPin size={20} aria-hidden="true" />
        NearBuzz
      </Link>
      <nav className="navbar-actions">
        {isAuthenticated ? (
          <>
            <Link to="/dashboard">Dashboard</Link>
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
