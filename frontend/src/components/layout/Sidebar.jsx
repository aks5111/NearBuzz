import { NavLink } from 'react-router-dom';
import { MapPin } from 'lucide-react';
import { ADMIN_MENU } from '../../modules/admin/config/menu';

export default function Sidebar() {
  return (
    <aside className="sidebar">
      <div className="sidebar-brand">
        <MapPin size={20} aria-hidden="true" />
        NearBuzz <span className="sidebar-brand-tag">Admin</span>
      </div>
      <nav className="sidebar-nav">
        {ADMIN_MENU.map(({ label, path, icon: Icon }) => (
          <NavLink
            key={path}
            to={path}
            className={({ isActive }) => `sidebar-link ${isActive ? 'sidebar-link-active' : ''}`}
          >
            <Icon size={18} aria-hidden="true" />
            {label}
          </NavLink>
        ))}
      </nav>
    </aside>
  );
}
