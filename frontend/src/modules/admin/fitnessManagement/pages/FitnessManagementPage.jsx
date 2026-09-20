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
        { name: 'gymName', label: 'Gym name', type: 'text' },
        { name: 'trainerPhone', label: 'Trainer phone', type: 'text' },
        { name: 'trainerEmail', label: 'Trainer email', type: 'text' },
        { name: 'trainerPhotoUrl', label: 'Trainer photo URL', type: 'text' },
      ]}
      extraColumns={[
        { key: 'trainerName', header: 'Trainer' },
        { key: 'gymName', header: 'Gym' },
      ]}
      api={{
        list: fetchAdminFitness,
        create: createAdminFitness,
        update: updateAdminFitness,
        remove: deleteAdminFitness,
      }}
    />
  );
}
