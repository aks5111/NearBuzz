import {
  LayoutDashboard,
  Users,
  ShieldCheck,
  Plane,
  Dumbbell,
  PersonStanding,
  PartyPopper,
  HeartHandshake,
  Trophy,
  Ticket,
  ShoppingBag,
  ClipboardList,
  Settings,
} from 'lucide-react';

export const ADMIN_MENU = [
  {
    section: 'Overview',
    items: [{ label: 'Dashboard', path: '/admin/dashboard', icon: LayoutDashboard }],
  },
  {
    section: 'Modules',
    items: [
      { label: 'Travel', path: '/admin/travel', icon: Plane },
      { label: 'Fitness', path: '/admin/fitness', icon: Dumbbell },
      { label: 'Calisthenics', path: '/admin/calisthenics', icon: PersonStanding },
      { label: 'Party Place', path: '/admin/party-place', icon: PartyPopper },
      { label: 'Meet Friends Nearby', path: '/admin/friends-nearby', icon: HeartHandshake },
      { label: 'Sports Activity', path: '/admin/sports-activity', icon: Trophy },
      { label: 'Event Booking', path: '/admin/event-booking', icon: Ticket },
      { label: 'Shopping', path: '/admin/shopping', icon: ShoppingBag },
    ],
  },
  {
    section: 'Administration',
    items: [
      { label: 'Users', path: '/admin/users', icon: Users },
      { label: 'Roles & Permissions', path: '/admin/roles', icon: ShieldCheck },
      { label: 'Bookings & Requests', path: '/admin/bookings', icon: ClipboardList },
      { label: 'Settings', path: '/admin/settings', icon: Settings },
    ],
  },
];
