import { Search, X } from 'lucide-react';

export default function SearchBar({ value, onChange }) {
  return (
    <div className="search-bar">
      <Search size={18} className="search-icon" aria-hidden="true" />
      <input
        type="text"
        placeholder="Search activities, places, or events near you…"
        value={value}
        onChange={(e) => onChange(e.target.value)}
        aria-label="Search activities"
      />
      {value && (
        <button type="button" className="search-clear" onClick={() => onChange('')} aria-label="Clear search">
          <X size={16} />
        </button>
      )}
    </div>
  );
}
