import { CATEGORIES } from '../mock/activities';

export default function CategoryFilter({ active, onChange }) {
  const chips = [{ id: 'all', label: 'All' }, ...CATEGORIES];
  return (
    <div className="category-filter" role="tablist" aria-label="Filter by category">
      {chips.map((chip) => (
        <button
          key={chip.id}
          type="button"
          role="tab"
          aria-selected={active === chip.id}
          className={`chip ${active === chip.id ? 'chip-active' : ''}`}
          onClick={() => onChange(chip.id)}
        >
          {chip.label}
        </button>
      ))}
    </div>
  );
}
