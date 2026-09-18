import * as Icons from 'lucide-react';
import { LayoutGrid } from 'lucide-react';

export default function CategoryTabs({ categories, active, onChange }) {
  return (
    <div className="category-filter" role="tablist" aria-label="Filter by category">
      <button
        type="button"
        role="tab"
        aria-selected={active === 'all'}
        className={`chip ${active === 'all' ? 'chip-active' : ''}`}
        onClick={() => onChange('all')}
      >
        <LayoutGrid size={14} aria-hidden="true" /> All
      </button>
      {categories.map((category) => {
        const Icon = Icons[category.icon] || Icons.Package;
        return (
          <button
            key={category.slug}
            type="button"
            role="tab"
            aria-selected={active === category.slug}
            className={`chip ${active === category.slug ? 'chip-active' : ''}`}
            onClick={() => onChange(category.slug)}
          >
            <Icon size={14} aria-hidden="true" /> {category.name}
          </button>
        );
      })}
    </div>
  );
}
