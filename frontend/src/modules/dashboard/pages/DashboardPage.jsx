import { useAuth } from '../../auth/hooks/useAuth';
import Button from '../../../components/ui/Button';

export default function DashboardPage() {
  const { logout } = useAuth();
  return (
    <div className="dashboard-page">
      <h1>Dashboard</h1>
      <p>You're logged in.</p>
      <Button variant="secondary" onClick={logout}>
        Log out
      </Button>
    </div>
  );
}
