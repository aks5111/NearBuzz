import { useMemo, useState } from 'react';
import { ACTIVITIES } from '../mock/activities';

export function useActivities() {
  const [query, setQuery] = useState('');
  const [activeCategory, setActiveCategory] = useState('all');

  const filtered = useMemo(() => {
    const normalizedQuery = query.trim().toLowerCase();
    return ACTIVITIES.filter((activity) => {
      const matchesCategory = activeCategory === 'all' || activity.category === activeCategory;
      if (!matchesCategory) return false;
      if (!normalizedQuery) return true;
      const haystack = `${activity.title} ${activity.location} ${activity.tag}`.toLowerCase();
      return haystack.includes(normalizedQuery);
    });
  }, [query, activeCategory]);

  return {
    activities: filtered,
    query,
    setQuery,
    activeCategory,
    setActiveCategory,
  };
}
