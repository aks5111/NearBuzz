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
      extraFields={[{ name: 'difficultyLevel', label: 'Difficulty (BEGINNER/INTERMEDIATE/ADVANCED)', type: 'text' }]}
      extraColumns={[{ key: 'difficultyLevel', header: 'Level' }]}
      api={{
        list: fetchAdminCalisthenics,
        create: createAdminCalisthenics,
        update: updateAdminCalisthenics,
        remove: deleteAdminCalisthenics,
      }}
    />
  );
}
