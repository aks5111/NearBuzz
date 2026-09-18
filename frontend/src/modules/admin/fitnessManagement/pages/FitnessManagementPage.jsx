import ListingManagementPage from '../../shared/components/ListingManagementPage';
import { fetchAdminFitness, createAdminFitness, updateAdminFitness, deleteAdminFitness } from '../../../../api/fitness.api';

export default function FitnessManagementPage() {
  return (
    <ListingManagementPage
      title="Fitness"
      subtitle="Manage fitness sessions shown on the public site."
      imageFolder="fitness"
      extraFields={[
        { name: 'trainerName', label: 'Trainer name', type: 'text' },
        { name: 'difficultyLevel', label: 'Difficulty (BEGINNER/INTERMEDIATE/ADVANCED)', type: 'text' },
      ]}
      extraColumns={[{ key: 'trainerName', header: 'Trainer' }, { key: 'difficultyLevel', header: 'Level' }]}
      api={{
        list: fetchAdminFitness,
        create: createAdminFitness,
        update: updateAdminFitness,
        remove: deleteAdminFitness,
      }}
    />
  );
}
