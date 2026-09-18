import { useEffect, useState } from 'react';
import { Compass } from 'lucide-react';
import SearchBar from '../../../components/ui/SearchBar';
import CategoryFilter from '../components/CategoryFilter';
import ActivityCard from '../components/ActivityCard';
import ProductCard from '../../shopping/components/ProductCard';
import { useActivities } from '../hooks/useActivities';
import { fetchShoppingProducts } from '../../../api/shopping.api';

export default function HomePage() {
  const { activities, query, setQuery, activeCategory, setActiveCategory } = useActivities();
  const [shoppingProducts, setShoppingProducts] = useState([]);
  const [shoppingLoading, setShoppingLoading] = useState(false);
  const isShoppingTab = activeCategory === 'shopping';

  useEffect(() => {
    if (!isShoppingTab) return;
    setShoppingLoading(true);
    fetchShoppingProducts({ search: query || undefined })
      .then(({ data }) => setShoppingProducts(data?.data ?? []))
      .catch(() => setShoppingProducts([]))
      .finally(() => setShoppingLoading(false));
  }, [isShoppingTab, query]);

  return (
    <div className="home-page">
      <section className="hero">
        <div className="hero-content">
          <span className="hero-badge">
            <Compass size={14} aria-hidden="true" /> Happening near you
          </span>
          <h1>Find something worth doing today</h1>
          <p>
            Treks, workouts, parties, sports, meetups, events and shopping — all in one place. Browse
            everything, or search for exactly what you're in the mood for.
          </p>
          <SearchBar
            value={query}
            onChange={setQuery}
            placeholder="Search activities, places, events or products…"
          />
        </div>
      </section>

      <section className="home-body">
        <CategoryFilter active={activeCategory} onChange={setActiveCategory} />

        {isShoppingTab ? (
          shoppingLoading ? (
            <p className="page-subtitle">Loading products…</p>
          ) : shoppingProducts.length > 0 ? (
            <div className="activity-grid">
              {shoppingProducts.map((product) => (
                <ProductCard key={product.id} product={product} />
              ))}
            </div>
          ) : (
            <div className="empty-state">
              <p>No products match "{query}" yet.</p>
            </div>
          )
        ) : activities.length > 0 ? (
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
