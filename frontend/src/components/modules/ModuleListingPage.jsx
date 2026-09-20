import { useEffect, useState } from 'react';
import SearchBar from '../ui/SearchBar';
import ModuleCard from './ModuleCard';

export default function ModuleListingPage({ title, tagline, searchPlaceholder, heroIcon: HeroIcon, fetchFn }) {
  const [query, setQuery] = useState('');
  const [items, setItems] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    setLoading(true);
    const timeout = setTimeout(() => {
      fetchFn(query)
        .then(({ data }) => setItems(data?.data ?? []))
        .catch((err) => setError(err.response?.data?.message || 'Failed to load'))
        .finally(() => setLoading(false));
    }, 200);
    return () => clearTimeout(timeout);
  }, [query, fetchFn]);

  return (
    <div className="home-page">
      <section className="hero">
        <div className="hero-content">
          <span className="hero-badge">
            {HeroIcon && <HeroIcon size={14} aria-hidden="true" />} {title}
          </span>
          <h1>{tagline}</h1>
          <SearchBar value={query} onChange={setQuery} placeholder={searchPlaceholder} />
        </div>
      </section>

      <section className="home-body">
        {loading && <p className="page-subtitle">Loading…</p>}
        {error && <p className="form-error">{error}</p>}
        {!loading && !error && items.length > 0 && (
          <div className="activity-grid">
            {items.map((item) => (
              <ModuleCard key={item.id} item={item} />
            ))}
          </div>
        )}
        {!loading && !error && items.length === 0 && (
          <div className="empty-state">
            <p>No listings match{query ? ` "${query}"` : ''} yet.</p>
          </div>
        )}
      </section>
    </div>
  );
}
