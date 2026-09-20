import ListingManagementPage from '../../shared/components/ListingManagementPage';
import {
  fetchAdminCalisthenics,
  createAdminCalisthenics,
  updateAdminCalisthenics,
  deleteAdminCalisthenics,
} from '../../../../api/calisthenics.api';

export default function CalisthenicsManagementPage() {
  return (
    <ListingManagementPage
      title="Calisthenics"
      subtitle="Manage calisthenics sessions shown on the public site."
      imageFolder="calisthenics"
      extraFields={[
        { name: 'difficultyLevel', label: 'Difficulty (BEGINNER/INTERMEDIATE/ADVANCED)', type: 'text' },
        { name: 'gymName', label: 'Gym name', type: 'text' },
        { name: 'trainerContactName', label: 'Trainer name', type: 'text' },
        { name: 'trainerPhone', label: 'Trainer phone', type: 'text' },
        { name: 'trainerEmail', label: 'Trainer email', type: 'text' },
        { name: 'trainerPhotoUrl', label: 'Trainer photo URL', type: 'text' },
      ]}
      extraColumns={[
        { key: 'difficultyLevel', header: 'Level' },
        { key: 'gymName', header: 'Gym' },
      ]}
      api={{
        list: fetchAdminCalisthenics,
        create: createAdminCalisthenics,
        update: updateAdminCalisthenics,
        remove: deleteAdminCalisthenics,
      }}
    />
  );
}
