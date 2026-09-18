import { useEffect, useRef, useState } from 'react';
import { LocateFixed, MapPin as MapPinIcon, Search, X } from 'lucide-react';
import { MapProvider, MODULE_META } from '../../../lib/map-provider';
import { fetchMapPins } from '../../../api/map.api';
import { searchPlaces } from '../../../api/geo.api';

const STYLE_OPTIONS = [
  { id: 'liberty', label: 'Streets' },
  { id: 'positron', label: 'Light' },
  { id: 'satellite', label: 'Satellite' },
];

export default function MapPage() {
  const containerRef = useRef(null);
  const providerRef = useRef(null);
  const pinsRef = useRef([]);
  const [pins, setPins] = useState([]);
  const [style, setStyle] = useState('liberty');
  const [query, setQuery] = useState('');
  const [selected, setSelected] = useState(null);
  const [searching, setSearching] = useState(false);
  const [searchError, setSearchError] = useState(null);

  useEffect(() => {
    const provider = new MapProvider(containerRef.current);
    providerRef.current = provider;

    provider.on('select', (properties) => setSelected(properties));

    provider.on('ready', async () => {
      try {
        const { lat, lng } = await provider.locate();
        provider.flyTo(lng, lat, 12);
      } catch {
        // geolocation denied/unavailable — keep the default India-wide view
      }
    });

    fetchMapPins()
      .then(({ data }) => {
        const list = data?.data ?? [];
        pinsRef.current = list;
        setPins(list);
        provider.setPins(list);
      })
      .catch(() => {});

    return () => provider.destroy();
  }, []);

  function handleStyleChange(nextStyle) {
    setStyle(nextStyle);
    providerRef.current?.setStyle(nextStyle);
  }

  function handleLocate() {
    providerRef.current
      ?.locate()
      .then(({ lat, lng }) => providerRef.current.flyTo(lng, lat, 13))
      .catch(() => setSearchError("Couldn't get your location. Check browser permissions."));
  }

  async function handleSearch(e) {
    e.preventDefault();
    const text = query.trim();
    if (!text) return;
    setSearching(true);
    setSearchError(null);

    // Local match against listings already on the map — works even
    // without a Google Places key, and is instant either way.
    const localMatch = pinsRef.current.find(
      (p) =>
        p.title.toLowerCase().includes(text.toLowerCase()) ||
        p.location?.toLowerCase().includes(text.toLowerCase())
    );

    try {
      const { data } = await searchPlaces(text);
      const results = data?.data?.results ?? [];
      if (results.length > 0) {
        providerRef.current.flyTo(results[0].longitude, results[0].latitude, 14);
        setSearching(false);
        return;
      }
    } catch {
      // Places not configured or failed — fall through to local match below.
    }

    if (localMatch) {
      providerRef.current.flyTo(localMatch.longitude, localMatch.latitude, 15);
      setSelected(localMatch);
    } else {
      setSearchError(`No match for "${text}"`);
    }
    setSearching(false);
  }

  return (
    <div className="map-page">
      <div className="map-toolbar">
        <form className="map-search" onSubmit={handleSearch}>
          <Search size={16} aria-hidden="true" />
          <input
            type="text"
            value={query}
            onChange={(e) => setQuery(e.target.value)}
            placeholder="Search places or listings…"
            aria-label="Search the map"
          />
          {searching && <span className="map-search-spinner" aria-hidden="true" />}
        </form>

        <div className="map-style-switch">
          {STYLE_OPTIONS.map((opt) => (
            <button
              key={opt.id}
              type="button"
              className={`chip ${style === opt.id ? 'chip-active' : ''}`}
              onClick={() => handleStyleChange(opt.id)}
            >
              {opt.label}
            </button>
          ))}
        </div>

        <button type="button" className="map-locate-btn" onClick={handleLocate} aria-label="Use my location">
          <LocateFixed size={16} aria-hidden="true" />
        </button>
      </div>

      {searchError && <p className="form-error map-search-error">{searchError}</p>}

      <div className="map-canvas" ref={containerRef} />

      <div className="map-legend">
        {Object.entries(MODULE_META).map(([key, meta]) => (
          <span className="map-legend-item" key={key}>
            <span className="map-legend-dot" style={{ background: meta.color }} />
            {meta.label}
          </span>
        ))}
      </div>

      {selected && (
        <div className="map-pin-panel">
          <button type="button" className="icon-btn map-pin-panel-close" onClick={() => setSelected(null)} aria-label="Close">
            <X size={16} />
          </button>
          {selected.imageUrl && <img src={selected.imageUrl} alt="" className="map-pin-panel-image" />}
          <span className="tag">{MODULE_META[selected.module]?.label ?? selected.module}</span>
          <h3>{selected.title}</h3>
          <p className="activity-card-meta">
            <MapPinIcon size={14} aria-hidden="true" /> {selected.location}
          </p>
          {selected.scheduleText && <p className="activity-card-meta">{selected.scheduleText}</p>}
          {selected.priceLabel && <p className="activity-card-price">{selected.priceLabel}</p>}
        </div>
      )}
    </div>
  );
}
