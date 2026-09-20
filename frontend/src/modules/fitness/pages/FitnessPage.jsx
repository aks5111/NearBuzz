import { Dumbbell } from 'lucide-react';
import ModuleListingPage from '../../../components/modules/ModuleListingPage';
import { fetchFitness } from '../../../api/fitness.api';

export default function FitnessPage() {
  return (
    <ModuleListingPage
      title="Fitness"
      tagline="Yoga, bootcamps and workouts happening near you"
      searchPlaceholder="Search fitness sessions or locations…"
      heroIcon={Dumbbell}
      fetchFn={fetchFitness}
    />
  );
}
