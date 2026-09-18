import { Compass } from 'lucide-react';
import SearchBar from '../components/SearchBar';
import CategoryFilter from '../components/CategoryFilter';
import ActivityCard from '../components/ActivityCard';
import { useActivities } from '../hooks/useActivities';

export default function HomePage() {
  const { activities, query, setQuery, activeCategory, setActiveCategory } = useActivities();

  return (
    <div className="home-page">
      <section className="hero">
        <div className="hero-content">
          <span className="hero-badge">
            <Compass size={14} aria-hidden="true" /> Happening near you
          </span>
          <h1>Find something worth doing today</h1>
          <p>
            Treks, workouts, parties, sports, meetups and events — all in one place. Browse everything,
            or search for exactly what you're in the mood for.
          </p>
          <SearchBar value={query} onChange={setQuery} />
        </div>
      </section>

      <section className="home-body">
        <CategoryFilter active={activeCategory} onChange={setActiveCategory} />

        {activities.length > 0 ? (
          <div className="activity-grid">
            {activities.map((activity) => (
              <ActivityCard key={activity.id} activity={activity} />
            ))}
          </div>
        ) : (
          <div className="empty-state">
            <p>Nothing matches "{query}" yet. Try a different search or category.</p>
          </div>
        )}
      </section>
    </div>
  );
}
