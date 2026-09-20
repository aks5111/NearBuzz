import { useEffect, useMemo, useState } from 'react';
import { Compass } from 'lucide-react';
import SearchBar from '../../../components/ui/SearchBar';
import CategoryPreviewCard from '../components/CategoryPreviewCard';
import { HOME_CATEGORIES } from '../config/categories';

export default function HomePage() {
  const [query, setQuery] = useState('');
  const [previews, setPreviews] = useState({});
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    Promise.allSettled(HOME_CATEGORIES.map((category) => category.fetchFn()))
      .then((results) => {
        const next = {};
        results.forEach((result, index) => {
          const key = HOME_CATEGORIES[index].key;
          if (result.status === 'fulfilled') {
            const items = result.value.data?.data ?? [];
            next[key] = { count: items.length, previewImage: items[0]?.imageUrl ?? null };
          } else {
            next[key] = { count: 0, previewImage: null };
          }
        });
        setPreviews(next);
      })
      .finally(() => setLoading(false));
  }, []);

  const filteredCategories = useMemo(() => {
    const normalized = query.trim().toLowerCase();
    if (!normalized) return HOME_CATEGORIES;
    return HOME_CATEGORIES.filter((c) => c.label.toLowerCase().includes(normalized));
  }, [query]);

  return (
    <div className="home-page">
      <section className="hero">
        <div className="hero-content">
          <span className="hero-badge">
            <Compass size={14} aria-hidden="true" /> Happening near you
          </span>
          <h1>Find something worth doing today</h1>
          <p>
            Treks, workouts, parties, sports, meetups, events and shopping — all in one place. Pick a
            category to explore.
          </p>
          <SearchBar value={query} onChange={setQuery} placeholder="Search categories… Travel, Fitness, Shopping" />
        </div>
      </section>

      <section className="home-body">
        <div className="category-preview-grid">
          {filteredCategories.map((category) => (
            <CategoryPreviewCard
              key={category.key}
              category={category}
              previewImage={previews[category.key]?.previewImage}
              count={previews[category.key]?.count}
              loading={loading}
            />
          ))}
        </div>
        {filteredCategories.length === 0 && (
          <div className="empty-state">
            <p>No category matches "{query}".</p>
          </div>
        )}
      </section>
    </div>
  );
}
