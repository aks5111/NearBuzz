import { useCallback, useEffect, useState } from 'react';
import { fetchShoppingCategories, fetchShoppingProducts } from '../../../api/shopping.api';

export function useShoppingProducts() {
  const [categories, setCategories] = useState([]);
  const [products, setProducts] = useState([]);
  const [activeCategory, setActiveCategory] = useState('all');
  const [query, setQuery] = useState('');
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetchShoppingCategories()
      .then(({ data }) => setCategories(data?.data ?? []))
      .catch(() => setCategories([]));
  }, []);

  const load = useCallback(() => {
    setLoading(true);
    setError(null);
    fetchShoppingProducts({
      category: activeCategory === 'all' ? undefined : activeCategory,
      search: query || undefined,
    })
      .then(({ data }) => setProducts(data?.data ?? []))
      .catch((err) => setError(err.response?.data?.message || 'Failed to load products'))
      .finally(() => setLoading(false));
  }, [activeCategory, query]);

  useEffect(() => {
    const timeout = setTimeout(load, 200);
    return () => clearTimeout(timeout);
  }, [load]);

  return {
    categories,
    products,
    activeCategory,
    setActiveCategory,
    query,
    setQuery,
    loading,
    error,
  };
}
