import { PartyPopper } from 'lucide-react';
import ModuleListingPage from '../../../components/modules/ModuleListingPage';
import { fetchPartyPlace } from '../../../api/partyplace.api';

export default function PartyPlacePage() {
  return (
    <ModuleListingPage
      title="Party Place"
      tagline="Lounges, rooftops and venues for your next night out"
      searchPlaceholder="Search party places or locations…"
      heroIcon={PartyPopper}
      fetchFn={fetchPartyPlace}
    />
  );
}
