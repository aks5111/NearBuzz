import { useEffect, useState } from 'react';
import { MapPin, Trophy } from 'lucide-react';
import SearchBar from '../../../components/ui/SearchBar';
import SportsActivityCard from '../components/SportsActivityCard';
import { fetchSportsActivity } from '../../../api/sportsactivity.api';

const POPULAR_AREAS = ['Bangalore', 'Mumbai'];

export default function SportsActivityPage() {
  const [query, setQuery] = useState('');
  const [items, setItems] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    setLoading(true);
    const timeout = setTimeout(() => {
      fetchSportsActivity(query)
        .then(({ data }) => setItems(data?.data ?? []))
        .catch((err) => setError(err.response?.data?.message || 'Failed to load'))
        .finally(() => setLoading(false));
    }, 200);
    return () => clearTimeout(timeout);
  }, [query]);

  return (
    <div className="home-page">
      <section className="hero">
        <div className="hero-content">
          <span className="hero-badge">
            <Trophy size={14} aria-hidden="true" /> Sports Activity
          </span>
          <h1>Find a game, league or venue near you</h1>
          <p>Search by area first, then browse every activity — timings, price and who organizes it.</p>

          <SearchBar value={query} onChange={setQuery} placeholder="Search by area — Bangalore, Mumbai…" />

          <div className="travel-destination-chips">
            {POPULAR_AREAS.map((area) => (
              <button
                key={area}
                type="button"
                className={`chip ${query === area ? 'chip-active' : ''}`}
                onClick={() => setQuery(query === area ? '' : area)}
              >
                <MapPin size={12} aria-hidden="true" /> {area}
              </button>
            ))}
          </div>
        </div>
      </section>

      <section className="home-body">
        {loading && <p className="page-subtitle">Loading…</p>}
        {error && <p className="form-error">{error}</p>}
        {!loading && !error && items.length > 0 && (
          <div className="travel-grid">
            {items.map((item) => (
              <SportsActivityCard key={item.id} item={item} />
            ))}
          </div>
        )}
        {!loading && !error && items.length === 0 && (
          <div className="empty-state">
            <p>No activities match "{query}" yet. Try another area.</p>
          </div>
        )}
      </section>
    </div>
  );
}
