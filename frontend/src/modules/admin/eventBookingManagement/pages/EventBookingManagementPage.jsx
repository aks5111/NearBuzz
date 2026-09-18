import ListingManagementPage from '../../shared/components/ListingManagementPage';
import {
  fetchAdminEventBooking,
  createAdminEventBooking,
  updateAdminEventBooking,
  deleteAdminEventBooking,
} from '../../../../api/eventbooking.api';

export default function EventBookingManagementPage() {
  return (
    <ListingManagementPage
      title="Event Booking"
      subtitle="Manage bookable events shown on the public site."
      imageFolder="eventbooking"
      extraFields={[
        { name: 'organizerName', label: 'Organizer', type: 'text' },
        { name: 'totalSeats', label: 'Total seats', type: 'number' },
        { name: 'seatsBooked', label: 'Seats booked', type: 'number' },
      ]}
      extraColumns={[
        { key: 'organizerName', header: 'Organizer' },
        { key: 'totalSeats', header: 'Seats' },
        { key: 'seatsBooked', header: 'Booked' },
      ]}
      api={{
        list: fetchAdminEventBooking,
        create: createAdminEventBooking,
        update: updateAdminEventBooking,
        remove: deleteAdminEventBooking,
      }}
    />
  );
}
