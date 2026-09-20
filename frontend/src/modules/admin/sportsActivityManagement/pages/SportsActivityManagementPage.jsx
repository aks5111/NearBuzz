import ListingManagementPage from '../../shared/components/ListingManagementPage';
import {
  fetchAdminSportsActivity,
  createAdminSportsActivity,
  updateAdminSportsActivity,
  deleteAdminSportsActivity,
} from '../../../../api/sportsactivity.api';

export default function SportsActivityManagementPage() {
  return (
    <ListingManagementPage
      title="Sports Activity"
      subtitle="Manage sports activities shown on the public site."
      imageFolder="sportsactivity"
      extraFields={[
        { name: 'sportType', label: 'Sport type', type: 'text' },
        { name: 'teamSize', label: 'Team size', type: 'number' },
        { name: 'venueName', label: 'Venue name', type: 'text' },
        { name: 'organizerName', label: 'Organizer name', type: 'text' },
        { name: 'organizerPhone', label: 'Organizer phone', type: 'text' },
        { name: 'organizerEmail', label: 'Organizer email', type: 'text' },
        { name: 'organizerPhotoUrl', label: 'Organizer photo URL', type: 'text' },
      ]}
      extraColumns={[
        { key: 'sportType', header: 'Sport' },
        { key: 'venueName', header: 'Venue' },
      ]}
      api={{
        list: fetchAdminSportsActivity,
        create: createAdminSportsActivity,
        update: updateAdminSportsActivity,
        remove: deleteAdminSportsActivity,
      }}
    />
  );
}
