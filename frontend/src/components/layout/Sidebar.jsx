import { NavLink } from 'react-router-dom';
import { MapPin } from 'lucide-react';
import { ADMIN_MENU } from '../../modules/admin/config/menu';
import { useAuth } from '../../modules/auth/hooks/useAuth';

export default function Sidebar() {
  const { user, role } = useAuth();
  const initial = user?.fullName?.trim()?.charAt(0)?.toUpperCase() || '?';

  return (
    <aside className="sidebar">
      <div className="sidebar-brand">
        <span className="sidebar-brand-icon">
          <MapPin size={18} aria-hidden="true" />
        </span>
        <div>
          <span className="sidebar-brand-name">NearBuzz</span>
          <span className="sidebar-brand-tag">Admin</span>
        </div>
      </div>

      <nav className="sidebar-nav">
        {ADMIN_MENU.map((group) => (
          <div className="sidebar-section" key={group.section}>
            <p className="sidebar-section-label">{group.section}</p>
            {group.items.map(({ label, path, icon: Icon }) => (
              <NavLink
                key={path}
                to={path}
                className={({ isActive }) => `sidebar-link ${isActive ? 'sidebar-link-active' : ''}`}
              >
                <span className="sidebar-link-icon">
                  <Icon size={16} aria-hidden="true" />
                </span>
                {label}
              </NavLink>
            ))}
          </div>
        ))}
      </nav>

      <div className="sidebar-user">
        <span className="sidebar-user-avatar">{initial}</span>
        <div className="sidebar-user-info">
          <span className="sidebar-user-name">{user?.fullName || 'Admin'}</span>
          <span className="sidebar-user-role">{role?.replace('ROLE_', '')}</span>
        </div>
      </div>
    </aside>
  );
}
