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
        { name: 'agencyName', label: 'Agency name', type: 'text' },
        { name: 'agencyContactName', label: 'Agency contact person', type: 'text' },
        { name: 'agencyPhone', label: 'Agency phone', type: 'text' },
        { name: 'agencyEmail', label: 'Agency email', type: 'text' },
        { name: 'agencyPhotoUrl', label: 'Agency contact photo URL', type: 'text' },
        { name: 'inclusions', label: "What's included", type: 'textarea' },
      ]}
      extraColumns={[
        { key: 'durationDays', header: 'Days' },
        { key: 'agencyName', header: 'Agency' },
      ]}
      api={{
        list: fetchAdminTravel,
        create: createAdminTravel,
        update: updateAdminTravel,
        remove: deleteAdminTravel,
      }}
    />
  );
}
