import ListingManagementPage from '../../shared/components/ListingManagementPage';
import {
  fetchAdminPartyPlace,
  createAdminPartyPlace,
  updateAdminPartyPlace,
  deleteAdminPartyPlace,
} from '../../../../api/partyplace.api';

export default function PartyPlaceManagementPage() {
  return (
    <ListingManagementPage
      title="Party Place"
      subtitle="Manage party place listings shown on the public site."
      imageFolder="partyplace"
      extraFields={[{ name: 'capacity', label: 'Capacity', type: 'number' }]}
      extraColumns={[{ key: 'capacity', header: 'Capacity' }]}
      api={{
        list: fetchAdminPartyPlace,
        create: createAdminPartyPlace,
        update: updateAdminPartyPlace,
        remove: deleteAdminPartyPlace,
      }}
    />
  );
}
