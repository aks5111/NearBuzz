import { Outlet } from 'react-router-dom';
import { LogOut } from 'lucide-react';
import Sidebar from '../components/layout/Sidebar';
import { useAuth } from '../modules/auth/hooks/useAuth';

export default function AdminLayout() {
  const { user, logout } = useAuth();

  return (
    <div className="admin-layout">
      <Sidebar />
      <div className="admin-main">
        <header className="admin-topbar">
          <div />
          <div className="admin-topbar-user">
            <span>{user?.fullName}</span>
            <button type="button" className="icon-btn" onClick={logout} aria-label="Log out">
              <LogOut size={18} />
            </button>
          </div>
        </header>
        <main className="admin-content">
          <Outlet />
        </main>
      </div>
    </div>
  );
}
