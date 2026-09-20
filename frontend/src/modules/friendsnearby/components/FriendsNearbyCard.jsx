import { Calendar, Mail, MapPin, Phone, Users } from 'lucide-react';

export default function FriendsNearbyCard({ item }) {
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
        {(item.minAge || item.maxParticipants) && (
          <p className="activity-card-meta">
            <Users size={14} aria-hidden="true" />
            {item.minAge ? ` ${item.minAge}+` : ''}
            {item.maxParticipants ? ` • up to ${item.maxParticipants} people` : ''}
          </p>
        )}

        <div className="activity-card-footer">
          <span className="activity-card-price">{item.priceLabel}</span>
        </div>

        {item.venueName && (
          <div className="travel-agency">
            {item.hostPhotoUrl && <img src={item.hostPhotoUrl} alt="" className="travel-agency-avatar" />}
            <div className="travel-agency-info">
              <p className="travel-agency-name">{item.venueName}</p>
              <p className="travel-agency-contact">{item.hostName}</p>
              <div className="travel-agency-links">
                {item.hostPhone && (
                  <a href={`tel:${item.hostPhone}`}>
                    <Phone size={12} aria-hidden="true" /> {item.hostPhone}
                  </a>
                )}
                {item.hostEmail && (
                  <a href={`mailto:${item.hostEmail}`}>
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
