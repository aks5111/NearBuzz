import { Calendar, CalendarDays, MapPin, Mail, Phone, Users } from 'lucide-react';

export default function TravelCard({ trip }) {
  const inclusions = (trip.inclusions || '')
    .split(',')
    .map((s) => s.trim())
    .filter(Boolean);

  return (
    <article className="travel-card">
      <div className="travel-card-image">
        <img src={trip.imageUrl} alt="" loading="lazy" />
        {trip.tag && <span className="activity-card-category">{trip.tag}</span>}
      </div>

      <div className="travel-card-body">
        <h3>{trip.title}</h3>
        <p className="activity-card-meta">
          <MapPin size={14} aria-hidden="true" /> {trip.location}
        </p>
        {trip.scheduleText && (
          <p className="activity-card-meta">
            <Calendar size={14} aria-hidden="true" /> {trip.scheduleText}
          </p>
        )}

        <div className="travel-card-stats">
          {trip.durationDays && (
            <span>
              <CalendarDays size={13} aria-hidden="true" /> {trip.durationDays}{' '}
              {trip.durationDays === 1 ? 'day' : 'days'}
            </span>
          )}
          {trip.groupSize && (
            <span>
              <Users size={13} aria-hidden="true" /> Up to {trip.groupSize}
            </span>
          )}
        </div>

        {inclusions.length > 0 && (
          <ul className="travel-card-inclusions">
            {inclusions.slice(0, 4).map((item) => (
              <li key={item}>{item}</li>
            ))}
            {inclusions.length > 4 && <li className="travel-card-more">+{inclusions.length - 4} more</li>}
          </ul>
        )}

        <div className="activity-card-footer">
          <span className="activity-card-price">{trip.priceLabel}</span>
        </div>

        {trip.agencyName && (
          <div className="travel-agency">
            {trip.agencyPhotoUrl && <img src={trip.agencyPhotoUrl} alt="" className="travel-agency-avatar" />}
            <div className="travel-agency-info">
              <p className="travel-agency-name">{trip.agencyName}</p>
              <p className="travel-agency-contact">{trip.agencyContactName}</p>
              <div className="travel-agency-links">
                {trip.agencyPhone && (
                  <a href={`tel:${trip.agencyPhone}`}>
                    <Phone size={12} aria-hidden="true" /> {trip.agencyPhone}
                  </a>
                )}
                {trip.agencyEmail && (
                  <a href={`mailto:${trip.agencyEmail}`}>
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
