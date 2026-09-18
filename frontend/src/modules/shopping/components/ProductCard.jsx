import { PackageCheck, PackageX } from 'lucide-react';

export default function ProductCard({ product }) {
  const inStock = product.stockQuantity > 0;

  return (
    <article className="activity-card">
      <div className="activity-card-image">
        <img src={product.imageUrl} alt="" loading="lazy" />
        <span className="activity-card-category">{product.category.name}</span>
      </div>
      <div className="activity-card-body">
        <h3>{product.title}</h3>
        {product.description && <p className="activity-card-meta">{product.description}</p>}
        <div className="activity-card-footer">
          <span className="activity-card-price">{product.priceLabel}</span>
          <span className={`tag ${inStock ? '' : 'tag-out-of-stock'}`}>
            {inStock ? (
              <>
                <PackageCheck size={12} aria-hidden="true" /> In stock
              </>
            ) : (
              <>
                <PackageX size={12} aria-hidden="true" /> Out of stock
              </>
            )}
          </span>
        </div>
      </div>
    </article>
  );
}
