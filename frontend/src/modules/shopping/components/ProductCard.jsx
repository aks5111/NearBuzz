import { Mail, MapPin, PackageCheck, PackageX, Phone } from 'lucide-react';

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
        {product.location && (
          <p className="activity-card-meta">
            <MapPin size={14} aria-hidden="true" /> {product.location}
          </p>
        )}
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

        {product.storeName && (
          <div className="travel-agency">
            {product.storePhotoUrl && <img src={product.storePhotoUrl} alt="" className="travel-agency-avatar" />}
            <div className="travel-agency-info">
              <p className="travel-agency-name">{product.storeName}</p>
              <p className="travel-agency-contact">{product.storeContactName}</p>
              <div className="travel-agency-links">
                {product.storePhone && (
                  <a href={`tel:${product.storePhone}`}>
                    <Phone size={12} aria-hidden="true" /> {product.storePhone}
                  </a>
                )}
                {product.storeEmail && (
                  <a href={`mailto:${product.storeEmail}`}>
                    <Mail size={12} aria-hidden="true" /> Email
                  </a>
                )}
              </div>
            </div>
          </div>
        )}
      </div>
    </article>
  );
}
