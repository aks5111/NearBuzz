import { Ticket } from 'lucide-react';
import ModuleListingPage from '../../../components/modules/ModuleListingPage';
import { fetchEventBooking } from '../../../api/eventbooking.api';

export default function EventBookingPage() {
  return (
    <ModuleListingPage
      title="Event Booking"
      tagline="Concerts, open mics and events worth booking"
      searchPlaceholder="Search events or locations…"
      heroIcon={Ticket}
      fetchFn={fetchEventBooking}
    />
  );
}
