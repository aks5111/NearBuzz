import { HeartHandshake } from 'lucide-react';
import ModuleListingPage from '../../../components/modules/ModuleListingPage';
import { fetchFriendsNearby } from '../../../api/friendsnearby.api';

export default function FriendsNearbyPage() {
  return (
    <ModuleListingPage
      title="Meet Friends Nearby"
      tagline="Meetups to make new friends in your city"
      searchPlaceholder="Search meetups or locations…"
      heroIcon={HeartHandshake}
      fetchFn={fetchFriendsNearby}
    />
  );
}
