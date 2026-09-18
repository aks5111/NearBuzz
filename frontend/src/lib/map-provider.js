// Thin wrapper around MapLibre GL JS (loaded via CDN in index.html, hence
// `window.maplibregl`) so the rest of the app never touches maplibregl
// directly. Swapping to Google Maps JS API later means rewriting this one
// file, not every screen that shows a map.

const STYLES = {
  liberty: 'https://tiles.openfreemap.org/styles/liberty',
  positron: 'https://tiles.openfreemap.org/styles/positron',
  satellite: {
    version: 8,
    sources: {
      esri: {
        type: 'raster',
        tiles: [
          'https://server.arcgisonline.com/ArcGIS/rest/services/World_Imagery/MapServer/tile/{z}/{y}/{x}',
        ],
        tileSize: 256,
        attribution: 'Esri, Maxar, Earthstar Geographics',
      },
    },
    layers: [{ id: 'esri', type: 'raster', source: 'esri' }],
  },
};

export const MODULE_META = {
  travel: { label: 'Travel', color: '#0ea5e9' },
  fitness: { label: 'Fitness', color: '#22c55e' },
  calisthenics: { label: 'Calisthenics', color: '#84cc16' },
  partyplace: { label: 'Party Place', color: '#f97316' },
  friendsnearby: { label: 'Meet Friends Nearby', color: '#ec4899' },
  sportsactivity: { label: 'Sports Activity', color: '#eab308' },
  eventbooking: { label: 'Event Booking', color: '#8b5cf6' },
};

const DEFAULT_CENTER = [78.9629, 20.5937]; // India, used when geolocation is denied/unavailable

export function pinsToGeoJSON(pins) {
  return {
    type: 'FeatureCollection',
    features: pins
      .filter((p) => p.latitude != null && p.longitude != null)
      .map((p) => ({
        type: 'Feature',
        geometry: { type: 'Point', coordinates: [p.longitude, p.latitude] },
        properties: { ...p },
      })),
  };
}

export class MapProvider {
  constructor(container, { style = 'liberty', center = DEFAULT_CENTER, zoom = 4.5 } = {}) {
    this.listeners = {};
    this.lastPinsData = { type: 'FeatureCollection', features: [] };

    this.map = new window.maplibregl.Map({
      container,
      style: STYLES[style] ?? STYLES.liberty,
      center,
      zoom,
    });
    this.map.addControl(new window.maplibregl.NavigationControl(), 'top-right');

    this.map.on('load', () => {
      this._addPinLayers();
      this._emit('ready');
    });
    this.map.on('error', (e) => this._emit('error', e));
    this.map.on('moveend', () =>
      this._emit('moveEnd', { center: this.map.getCenter(), zoom: this.map.getZoom() })
    );
  }

  on(event, callback) {
    (this.listeners[event] ??= []).push(callback);
    return () => {
      this.listeners[event] = (this.listeners[event] || []).filter((fn) => fn !== callback);
    };
  }

  _emit(event, payload) {
    (this.listeners[event] || []).forEach((cb) => cb(payload));
  }

  _addPinLayers() {
    if (this.map.getSource('pins')) return;

    this.map.addSource('pins', {
      type: 'geojson',
      data: this.lastPinsData,
      cluster: true,
      clusterMaxZoom: 14,
      clusterRadius: 50,
    });

    this.map.addLayer({
      id: 'clusters',
      type: 'circle',
      source: 'pins',
      filter: ['has', 'point_count'],
      paint: {
        'circle-color': '#4f46e5',
        'circle-radius': ['step', ['get', 'point_count'], 16, 10, 20, 30, 26],
        'circle-opacity': 0.85,
      },
    });

    this.map.addLayer({
      id: 'cluster-count',
      type: 'symbol',
      source: 'pins',
      filter: ['has', 'point_count'],
      layout: { 'text-field': '{point_count_abbreviated}', 'text-size': 12 },
      paint: { 'text-color': '#fff' },
    });

    const colorMatch = ['match', ['get', 'module']];
    Object.entries(MODULE_META).forEach(([key, meta]) => colorMatch.push(key, meta.color));
    colorMatch.push('#6b7280');

    this.map.addLayer({
      id: 'unclustered-point',
      type: 'circle',
      source: 'pins',
      filter: ['!', ['has', 'point_count']],
      paint: {
        'circle-color': colorMatch,
        'circle-radius': 8,
        'circle-stroke-width': 2,
        'circle-stroke-color': '#ffffff',
      },
    });

    this.map.on('click', 'unclustered-point', (e) => {
      const feature = e.features[0];
      this._emit('select', feature.properties);
    });

    this.map.on('click', 'clusters', (e) => {
      const features = this.map.queryRenderedFeatures(e.point, { layers: ['clusters'] });
      const clusterId = features[0].properties.cluster_id;
      this.map.getSource('pins').getClusterExpansionZoom(clusterId, (err, zoom) => {
        if (err) return;
        this.map.easeTo({ center: features[0].geometry.coordinates, zoom });
      });
    });

    ['unclustered-point', 'clusters'].forEach((layer) => {
      this.map.on('mouseenter', layer, () => {
        this.map.getCanvas().style.cursor = 'pointer';
      });
      this.map.on('mouseleave', layer, () => {
        this.map.getCanvas().style.cursor = '';
      });
    });
  }

  setPins(pins) {
    this.lastPinsData = pinsToGeoJSON(pins);
    const source = this.map.getSource('pins');
    if (source) {
      source.setData(this.lastPinsData);
    }
  }

  setStyle(styleName) {
    this.map.setStyle(STYLES[styleName] ?? STYLES.liberty);
    this.map.once('styledata', () => this._addPinLayers());
  }

  flyTo(lng, lat, zoom = 14) {
    this.map.flyTo({ center: [lng, lat], zoom });
  }

  locate() {
    return new Promise((resolve, reject) => {
      if (!navigator.geolocation) {
        reject(new Error('Geolocation not supported by this browser'));
        return;
      }
      navigator.geolocation.getCurrentPosition(
        (pos) => resolve({ lat: pos.coords.latitude, lng: pos.coords.longitude }),
        (err) => reject(err),
        { timeout: 8000 }
      );
    });
  }

  destroy() {
    this.map.remove();
  }
}
