import { Calendar, MapPin } from 'lucide-react';
import { CATEGORIES } from '../mock/activities';

export default function ActivityCard({ activity }) {
  const categoryLabel = CATEGORIES.find((c) => c.id === activity.category)?.label ?? activity.category;

  return (
    <article className="activity-card">
      <div className="activity-card-image">
        <img src={activity.image} alt="" loading="lazy" />
        <span className="activity-card-category">{categoryLabel}</span>
      </div>
      <div className="activity-card-body">
        <h3>{activity.title}</h3>
        <p className="activity-card-meta">
          <MapPin size={14} aria-hidden="true" /> {activity.location}
        </p>
        <p className="activity-card-meta">
          <Calendar size={14} aria-hidden="true" /> {activity.schedule}
        </p>
        <div className="activity-card-footer">
          <span className="activity-card-price">{activity.price}</span>
          <span className="tag">{activity.tag}</span>
        </div>
      </div>
    </article>
  );
}
