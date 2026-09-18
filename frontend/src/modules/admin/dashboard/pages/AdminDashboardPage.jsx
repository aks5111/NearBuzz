import { Users, Plane, Ticket, ClipboardList } from 'lucide-react';

const STAT_CARDS = [
  { label: 'Total Users', value: '—', icon: Users },
  { label: 'Active Listings', value: '—', icon: Plane },
  { label: 'Events This Week', value: '—', icon: Ticket },
  { label: 'Pending Bookings', value: '—', icon: ClipboardList },
];

export default function AdminDashboardPage() {
  return (
    <div className="admin-dashboard">
      <h1>Dashboard</h1>
      <p className="page-subtitle">Overview across every module. Numbers wire up once each module's API is built.</p>
      <div className="stat-grid">
        {STAT_CARDS.map(({ label, value, icon: Icon }) => (
          <div className="stat-card" key={label}>
            <Icon size={20} aria-hidden="true" />
            <div>
              <p className="stat-value">{value}</p>
              <p className="stat-label">{label}</p>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
