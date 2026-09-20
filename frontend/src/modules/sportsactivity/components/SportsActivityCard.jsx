import { Calendar, Mail, MapPin, Phone, Trophy } from 'lucide-react';

export default function SportsActivityCard({ item }) {
  return (
    <article className="travel-card">
      <div className="travel-card-image">
        <img src={item.imageUrl} alt="" loading="lazy" />
        {item.tag && <span className="activity-card-category">{item.tag}</span>}
      </div>

      <div className="travel-card-body">
        <h3>{item.title}</h3>
        <p className="activity-card-meta">
          <MapPin size={14} aria-hidden="true" /> {item.location}
        </p>
        {item.scheduleText && (
          <p className="activity-card-meta">
            <Calendar size={14} aria-hidden="true" /> {item.scheduleText}
          </p>
        )}
        {item.sportType && (
          <p className="activity-card-meta">
            <Trophy size={14} aria-hidden="true" /> {item.sportType}
          </p>
        )}

        <div className="activity-card-footer">
          <span className="activity-card-price">{item.priceLabel}</span>
        </div>

        {item.venueName && (
          <div className="travel-agency">
            {item.organizerPhotoUrl && (
              <img src={item.organizerPhotoUrl} alt="" className="travel-agency-avatar" />
            )}
            <div className="travel-agency-info">
              <p className="travel-agency-name">{item.venueName}</p>
              <p className="travel-agency-contact">{item.organizerName}</p>
              <div className="travel-agency-links">
                {item.organizerPhone && (
                  <a href={`tel:${item.organizerPhone}`}>
                    <Phone size={12} aria-hidden="true" /> {item.organizerPhone}
                  </a>
                )}
                {item.organizerEmail && (
                  <a href={`mailto:${item.organizerEmail}`}>
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
