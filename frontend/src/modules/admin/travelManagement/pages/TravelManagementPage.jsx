import ListingManagementPage from '../../shared/components/ListingManagementPage';
import { fetchAdminTravel, createAdminTravel, updateAdminTravel, deleteAdminTravel } from '../../../../api/travel.api';

export default function TravelManagementPage() {
  return (
    <ListingManagementPage
      title="Travel"
      subtitle="Manage travel packages shown on the public site."
      imageFolder="travel"
      extraFields={[
        { name: 'durationDays', label: 'Duration (days)', type: 'number' },
        { name: 'groupSize', label: 'Group size', type: 'number' },
      ]}
      extraColumns={[{ key: 'durationDays', header: 'Days' }, { key: 'groupSize', header: 'Group' }]}
      api={{
        list: fetchAdminTravel,
        create: createAdminTravel,
        update: updateAdminTravel,
        remove: deleteAdminTravel,
      }}
    />
  );
}
