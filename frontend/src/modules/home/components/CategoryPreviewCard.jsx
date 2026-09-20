import { Link } from 'react-router-dom';
import { ArrowRight } from 'lucide-react';

export default function CategoryPreviewCard({ category, previewImage, count, loading }) {
  const Icon = category.icon;

  return (
    <Link to={category.path} className="category-preview-card">
      <div className="category-preview-image">
        {previewImage ? (
          <img src={previewImage} alt="" loading="lazy" />
        ) : (
          <div className="category-preview-fallback">
            <Icon size={28} aria-hidden="true" />
          </div>
        )}
      </div>
      <div className="category-preview-body">
        <div className="category-preview-title">
          <Icon size={16} aria-hidden="true" />
          <h3>{category.label}</h3>
        </div>
        <p>{loading ? 'Loading…' : `${count ?? 0} listing${count === 1 ? '' : 's'}`}</p>
      </div>
      <ArrowRight size={16} className="category-preview-arrow" aria-hidden="true" />
    </Link>
  );
}
