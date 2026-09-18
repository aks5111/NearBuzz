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
      ]}
      extraColumns={[{ key: 'sportType', header: 'Sport' }, { key: 'teamSize', header: 'Team size' }]}
      api={{
        list: fetchAdminSportsActivity,
        create: createAdminSportsActivity,
        update: updateAdminSportsActivity,
        remove: deleteAdminSportsActivity,
      }}
    />
  );
}
