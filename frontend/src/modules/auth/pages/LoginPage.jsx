import { Link } from 'react-router-dom';
import Card from '../../../components/ui/Card';
import LoginForm from '../components/LoginForm';

export default function LoginPage() {
  return (
    <Card className="auth-card">
      <h1>Welcome back</h1>
      <p className="auth-subtitle">Log in to continue to NearBuzz</p>
      <LoginForm />
      <p className="auth-footer">
        Don't have an account? <Link to="/register">Sign up</Link>
      </p>
    </Card>
  );
}
