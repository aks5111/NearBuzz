import { Outlet } from 'react-router-dom';
import Navbar from '../components/layout/Navbar';
import ChatWidget from '../modules/chat/components/ChatWidget';

export default function MainLayout() {
  return (
    <div className="main-layout">
      <Navbar />
      <main>
        <Outlet />
      </main>
      <ChatWidget />
    </div>
  );
}
