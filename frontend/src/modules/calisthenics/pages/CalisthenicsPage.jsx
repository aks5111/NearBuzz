import { useEffect, useState } from 'react';
import { MapPin, PersonStanding } from 'lucide-react';
import SearchBar from '../../../components/ui/SearchBar';
import CalisthenicsCard from '../components/CalisthenicsCard';
import { fetchCalisthenics } from '../../../api/calisthenics.api';

const POPULAR_AREAS = ['Bangalore', 'Mumbai', 'Gurgaon'];

export default function CalisthenicsPage() {
  const [query, setQuery] = useState('');
  const [items, setItems] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    setLoading(true);
    const timeout = setTimeout(() => {
      fetchCalisthenics(query)
        .then(({ data }) => setItems(data?.data ?? []))
        .catch((err) => setError(err.response?.data?.message || 'Failed to load classes'))
        .finally(() => setLoading(false));
    }, 200);
    return () => clearTimeout(timeout);
  }, [query]);

  return (
    <div className="home-page">
      <section className="hero">
        <div className="hero-content">
          <span className="hero-badge">
            <PersonStanding size={14} aria-hidden="true" /> Calisthenics
          </span>
          <h1>Find a class, trainer or gym near you</h1>
          <p>Search by area first, then browse every class — timings, difficulty and who's coaching it.</p>

          <SearchBar value={query} onChange={setQuery} placeholder="Search by area — Bangalore, Mumbai, Gurgaon…" />

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
        {loading && <p className="page-subtitle">Loading classes…</p>}
        {error && <p className="form-error">{error}</p>}
        {!loading && !error && items.length > 0 && (
          <div className="travel-grid">
            {items.map((item) => (
              <CalisthenicsCard key={item.id} item={item} />
            ))}
          </div>
        )}
        {!loading && !error && items.length === 0 && (
          <div className="empty-state">
            <p>No classes match "{query}" yet. Try another area.</p>
          </div>
        )}
      </section>
    </div>
  );
}
