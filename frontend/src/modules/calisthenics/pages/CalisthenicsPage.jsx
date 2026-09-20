import { PersonStanding } from 'lucide-react';
import ModuleListingPage from '../../../components/modules/ModuleListingPage';
import { fetchCalisthenics } from '../../../api/calisthenics.api';

export default function CalisthenicsPage() {
  return (
    <ModuleListingPage
      title="Calisthenics"
      tagline="Street workout meetups and bodyweight training groups"
      searchPlaceholder="Search calisthenics sessions or locations…"
      heroIcon={PersonStanding}
      fetchFn={fetchCalisthenics}
    />
  );
}
