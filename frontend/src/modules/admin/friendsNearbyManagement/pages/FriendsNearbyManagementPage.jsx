import ListingManagementPage from '../../shared/components/ListingManagementPage';
import {
  fetchAdminFriendsNearby,
  createAdminFriendsNearby,
  updateAdminFriendsNearby,
  deleteAdminFriendsNearby,
} from '../../../../api/friendsnearby.api';

export default function FriendsNearbyManagementPage() {
  return (
    <ListingManagementPage
      title="Meet Friends Nearby"
      subtitle="Manage meetups shown on the public site."
      imageFolder="friendsnearby"
      extraFields={[
        { name: 'minAge', label: 'Minimum age', type: 'number' },
        { name: 'maxParticipants', label: 'Max participants', type: 'number' },
      ]}
      extraColumns={[{ key: 'minAge', header: 'Min age' }, { key: 'maxParticipants', header: 'Max' }]}
      api={{
        list: fetchAdminFriendsNearby,
        create: createAdminFriendsNearby,
        update: updateAdminFriendsNearby,
        remove: deleteAdminFriendsNearby,
      }}
    />
  );
}
