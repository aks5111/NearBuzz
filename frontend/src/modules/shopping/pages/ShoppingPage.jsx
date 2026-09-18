import { ShoppingBag } from 'lucide-react';
import SearchBar from '../../../components/ui/SearchBar';
import CategoryTabs from '../components/CategoryTabs';
import ProductCard from '../components/ProductCard';
import { useShoppingProducts } from '../hooks/useShoppingProducts';

export default function ShoppingPage() {
  const { categories, products, activeCategory, setActiveCategory, query, setQuery, loading, error } =
    useShoppingProducts();

  return (
    <div className="home-page">
      <section className="hero">
        <div className="hero-content">
          <span className="hero-badge">
            <ShoppingBag size={14} aria-hidden="true" /> Shop everything nearby
          </span>
          <h1>Grocery, fashion, electronics and more</h1>
          <p>Browse every category, or search for exactly what you need.</p>
          <SearchBar value={query} onChange={setQuery} placeholder="Search products…" />
        </div>
      </section>

      <section className="home-body">
        <CategoryTabs categories={categories} active={activeCategory} onChange={setActiveCategory} />

        {loading && <p className="page-subtitle">Loading products…</p>}
        {error && <p className="form-error">{error}</p>}

        {!loading && !error && products.length > 0 && (
          <div className="activity-grid">
            {products.map((product) => (
              <ProductCard key={product.id} product={product} />
            ))}
          </div>
        )}

        {!loading && !error && products.length === 0 && (
          <div className="empty-state">
            <p>No products match{query ? ` "${query}"` : ' this filter'} yet.</p>
          </div>
        )}
      </section>
    </div>
  );
}
