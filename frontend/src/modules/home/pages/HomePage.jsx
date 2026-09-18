import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { ArrowRight, Compass, ShoppingBag } from 'lucide-react';
import SearchBar from '../../../components/ui/SearchBar';
import CategoryFilter from '../components/CategoryFilter';
import ActivityCard from '../components/ActivityCard';
import ProductCard from '../../shopping/components/ProductCard';
import { useActivities } from '../hooks/useActivities';
import { fetchShoppingProducts } from '../../../api/shopping.api';

const SHOPPING_PREVIEW_COUNT = 4;

export default function HomePage() {
  const { activities, query, setQuery, activeCategory, setActiveCategory } = useActivities();
  const [shoppingPreview, setShoppingPreview] = useState([]);

  useEffect(() => {
    fetchShoppingProducts()
      .then(({ data }) => setShoppingPreview((data?.data ?? []).slice(0, SHOPPING_PREVIEW_COUNT)))
      .catch(() => setShoppingPreview([]));
  }, []);

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
          <SearchBar
            value={query}
            onChange={setQuery}
            placeholder="Search activities, places, or events near you…"
          />
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

        {shoppingPreview.length > 0 && (
          <div className="section-header-row">
            <h2>
              <ShoppingBag size={18} aria-hidden="true" /> Shop nearby
            </h2>
            <Link to="/shopping" className="section-see-all">
              See all <ArrowRight size={14} aria-hidden="true" />
            </Link>
          </div>
        )}

        {shoppingPreview.length > 0 && (
          <div className="activity-grid">
            {shoppingPreview.map((product) => (
              <ProductCard key={product.id} product={product} />
            ))}
          </div>
        )}
      </section>
    </div>
  );
}
