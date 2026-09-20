import { Navigate, Route, Routes } from 'react-router-dom';
import AuthLayout from '../layouts/AuthLayout';
import MainLayout from '../layouts/MainLayout';
import AdminLayout from '../layouts/AdminLayout';
import LoginPage from '../modules/auth/pages/LoginPage';
import HomePage from '../modules/home/pages/HomePage';
import ShoppingPage from '../modules/shopping/pages/ShoppingPage';
import MapPage from '../modules/map/pages/MapPage';
import TravelPage from '../modules/travel/pages/TravelPage';
import FitnessPage from '../modules/fitness/pages/FitnessPage';
import CalisthenicsPage from '../modules/calisthenics/pages/CalisthenicsPage';
import PartyPlacePage from '../modules/partyplace/pages/PartyPlacePage';
import FriendsNearbyPage from '../modules/friendsnearby/pages/FriendsNearbyPage';
import SportsActivityPage from '../modules/sportsactivity/pages/SportsActivityPage';
import EventBookingPage from '../modules/eventbooking/pages/EventBookingPage';
import DashboardPage from '../modules/dashboard/pages/DashboardPage';
import AdminLoginPage from '../modules/admin/login/pages/AdminLoginPage';
import AdminDashboardPage from '../modules/admin/dashboard/pages/AdminDashboardPage';
import UserManagementPage from '../modules/admin/userManagement/pages/UserManagementPage';
import RolesPermissionsPage from '../modules/admin/roles/pages/RolesPermissionsPage';
import TravelManagementPage from '../modules/admin/travelManagement/pages/TravelManagementPage';
import FitnessManagementPage from '../modules/admin/fitnessManagement/pages/FitnessManagementPage';
import CalisthenicsManagementPage from '../modules/admin/calisthenicsManagement/pages/CalisthenicsManagementPage';
import PartyPlaceManagementPage from '../modules/admin/partyPlaceManagement/pages/PartyPlaceManagementPage';
import FriendsNearbyManagementPage from '../modules/admin/friendsNearbyManagement/pages/FriendsNearbyManagementPage';
import SportsActivityManagementPage from '../modules/admin/sportsActivityManagement/pages/SportsActivityManagementPage';
import EventBookingManagementPage from '../modules/admin/eventBookingManagement/pages/EventBookingManagementPage';
import ShoppingManagementPage from '../modules/admin/shoppingManagement/pages/ShoppingManagementPage';
import BookingsPage from '../modules/admin/bookings/pages/BookingsPage';
import SettingsPage from '../modules/admin/settings/pages/SettingsPage';
import PrivateRoute from './PrivateRoute';
import AdminRoute from './AdminRoute';

export default function AppRoutes() {
  return (
    <Routes>
      <Route element={<MainLayout />}>
        <Route path="/" element={<HomePage />} />
        <Route path="/shopping" element={<ShoppingPage />} />
        <Route path="/map" element={<MapPage />} />
        <Route path="/travel" element={<TravelPage />} />
        <Route path="/fitness" element={<FitnessPage />} />
        <Route path="/calisthenics" element={<CalisthenicsPage />} />
        <Route path="/party-place" element={<PartyPlacePage />} />
        <Route path="/friends-nearby" element={<FriendsNearbyPage />} />
        <Route path="/sports-activity" element={<SportsActivityPage />} />
        <Route path="/event-booking" element={<EventBookingPage />} />
      </Route>

      <Route element={<AuthLayout />}>
        <Route path="/login" element={<LoginPage />} />
        <Route path="/admin/login" element={<AdminLoginPage />} />
      </Route>

      <Route element={<PrivateRoute />}>
        <Route element={<MainLayout />}>
          <Route path="/dashboard" element={<DashboardPage />} />
        </Route>
      </Route>

      <Route element={<AdminRoute />}>
        <Route element={<AdminLayout />}>
          <Route path="/admin/dashboard" element={<AdminDashboardPage />} />
          <Route path="/admin/users" element={<UserManagementPage />} />
          <Route path="/admin/roles" element={<RolesPermissionsPage />} />
          <Route path="/admin/travel" element={<TravelManagementPage />} />
          <Route path="/admin/fitness" element={<FitnessManagementPage />} />
          <Route path="/admin/calisthenics" element={<CalisthenicsManagementPage />} />
          <Route path="/admin/party-place" element={<PartyPlaceManagementPage />} />
          <Route path="/admin/friends-nearby" element={<FriendsNearbyManagementPage />} />
          <Route path="/admin/sports-activity" element={<SportsActivityManagementPage />} />
          <Route path="/admin/event-booking" element={<EventBookingManagementPage />} />
          <Route path="/admin/shopping" element={<ShoppingManagementPage />} />
          <Route path="/admin/bookings" element={<BookingsPage />} />
          <Route path="/admin/settings" element={<SettingsPage />} />
        </Route>
      </Route>

      <Route path="*" element={<Navigate to="/" replace />} />
    </Routes>
  );
}
