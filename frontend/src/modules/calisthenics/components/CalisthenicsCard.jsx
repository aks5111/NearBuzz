import { Calendar, Mail, MapPin, Phone, Signal } from 'lucide-react';

export default function CalisthenicsCard({ item }) {
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
        {item.difficultyLevel && (
          <p className="activity-card-meta">
            <Signal size={14} aria-hidden="true" /> {item.difficultyLevel}
          </p>
        )}

        <div className="activity-card-footer">
          <span className="activity-card-price">{item.priceLabel}</span>
        </div>

        {item.gymName && (
          <div className="travel-agency">
            {item.trainerPhotoUrl && <img src={item.trainerPhotoUrl} alt="" className="travel-agency-avatar" />}
            <div className="travel-agency-info">
              <p className="travel-agency-name">{item.gymName}</p>
              <p className="travel-agency-contact">{item.trainerContactName}</p>
              <div className="travel-agency-links">
                {item.trainerPhone && (
                  <a href={`tel:${item.trainerPhone}`}>
                    <Phone size={12} aria-hidden="true" /> {item.trainerPhone}
                  </a>
                )}
                {item.trainerEmail && (
                  <a href={`mailto:${item.trainerEmail}`}>
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
