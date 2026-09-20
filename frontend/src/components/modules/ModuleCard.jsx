import { Calendar, MapPin } from 'lucide-react';

export default function ModuleCard({ item }) {
  return (
    <article className="activity-card">
      <div className="activity-card-image">
        <img src={item.imageUrl} alt="" loading="lazy" />
        {item.tag && <span className="activity-card-category">{item.tag}</span>}
      </div>
      <div className="activity-card-body">
        <h3>{item.title}</h3>
        <p className="activity-card-meta">
          <MapPin size={14} aria-hidden="true" /> {item.location}
        </p>
        {item.scheduleText && (
          <p className="activity-card-meta">
            <Calendar size={14} aria-hidden="true" /> {item.scheduleText}
          </p>
        )}
        <div className="activity-card-footer">
          {item.priceLabel && <span className="activity-card-price">{item.priceLabel}</span>}
        </div>
      </div>
    </article>
  );
}
