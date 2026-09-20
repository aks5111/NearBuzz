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
        { name: 'venueName', label: 'Venue name', type: 'text' },
        { name: 'hostName', label: 'Host name', type: 'text' },
        { name: 'hostPhone', label: 'Host phone', type: 'text' },
        { name: 'hostEmail', label: 'Host email', type: 'text' },
        { name: 'hostPhotoUrl', label: 'Host photo URL', type: 'text' },
      ]}
      extraColumns={[
        { key: 'minAge', header: 'Min age' },
        { key: 'venueName', header: 'Venue' },
      ]}
      api={{
        list: fetchAdminFriendsNearby,
        create: createAdminFriendsNearby,
        update: updateAdminFriendsNearby,
        remove: deleteAdminFriendsNearby,
      }}
    />
  );
}
