import { Trophy } from 'lucide-react';
import ModuleListingPage from '../../../components/modules/ModuleListingPage';
import { fetchSportsActivity } from '../../../api/sportsactivity.api';

export default function SportsActivityPage() {
  return (
    <ModuleListingPage
      title="Sports Activity"
      tagline="Leagues, matches and pickup games near you"
      searchPlaceholder="Search sports activities or locations…"
      heroIcon={Trophy}
      fetchFn={fetchSportsActivity}
    />
  );
}
