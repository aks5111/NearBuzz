import { useEffect, useState } from 'react';
import { Compass, MapPin } from 'lucide-react';
import SearchBar from '../../../components/ui/SearchBar';
import TravelCard from '../components/TravelCard';
import { fetchTravel } from '../../../api/travel.api';

const POPULAR_DESTINATIONS = ['Coorg', 'Wayanad', 'Pune', 'Kodaikanal', 'Goa'];

export default function TravelPage() {
  const [query, setQuery] = useState('');
  const [travelDate, setTravelDate] = useState('');
  const [trips, setTrips] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    setLoading(true);
    const timeout = setTimeout(() => {
      fetchTravel(query)
        .then(({ data }) => setTrips(data?.data ?? []))
        .catch((err) => setError(err.response?.data?.message || 'Failed to load travel packages'))
        .finally(() => setLoading(false));
    }, 200);
    return () => clearTimeout(timeout);
  }, [query]);

  return (
    <div className="home-page">
      <section className="hero">
        <div className="hero-content">
          <span className="hero-badge">
            <Compass size={14} aria-hidden="true" /> Travel
          </span>
          <h1>Where do you want to go next?</h1>
          <p>Search by destination, browse popular getaways, and see exactly who runs each trip.</p>

          <div className="travel-search-row">
            <SearchBar value={query} onChange={setQuery} placeholder="Search by area — Coorg, Wayanad, Goa…" />
            <div className="travel-date-field">
              <label htmlFor="travelDate">Travel date</label>
              <input
                id="travelDate"
                type="date"
                className="input"
                value={travelDate}
                onChange={(e) => setTravelDate(e.target.value)}
              />
            </div>
          </div>
          {travelDate && (
            <p className="travel-date-note">
              Showing all available packages — exact date filtering is coming soon.
            </p>
          )}

          <div className="travel-destination-chips">
            {POPULAR_DESTINATIONS.map((place) => (
              <button
                key={place}
                type="button"
                className={`chip ${query === place ? 'chip-active' : ''}`}
                onClick={() => setQuery(query === place ? '' : place)}
              >
                <MapPin size={12} aria-hidden="true" /> {place}
              </button>
            ))}
          </div>
        </div>
      </section>

      <section className="home-body">
        {loading && <p className="page-subtitle">Loading trips…</p>}
        {error && <p className="form-error">{error}</p>}
        {!loading && !error && trips.length > 0 && (
          <div className="travel-grid">
            {trips.map((trip) => (
              <TravelCard key={trip.id} trip={trip} />
            ))}
          </div>
        )}
        {!loading && !error && trips.length === 0 && (
          <div className="empty-state">
            <p>No trips match "{query}" yet. Try another destination.</p>
          </div>
        )}
      </section>
    </div>
  );
}
