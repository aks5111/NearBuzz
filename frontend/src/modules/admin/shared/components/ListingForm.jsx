import { useState } from 'react';
import Button from '../../../../components/ui/Button';
import Input from '../../../../components/ui/Input';
import MultiImageUploader from '../../../../components/ui/MultiImageUploader';

const STATUSES = ['DRAFT', 'PUBLISHED', 'ARCHIVED'];

function buildInitialForm(initial, extraFields) {
  const base = {
    title: initial?.title ?? '',
    description: initial?.description ?? '',
    location: initial?.location ?? '',
    scheduleText: initial?.scheduleText ?? '',
    priceLabel: initial?.priceLabel ?? '',
    tag: initial?.tag ?? '',
    imageUrls: initial?.imageUrls ?? [],
    latitude: initial?.latitude ?? '',
    longitude: initial?.longitude ?? '',
    status: initial?.status ?? 'PUBLISHED',
  };
  extraFields.forEach((field) => {
    base[field.name] = initial?.[field.name] ?? (field.type === 'number' ? '' : '');
  });
  return base;
}

export default function ListingForm({ initial, extraFields = [], imageFolder, onSubmit, onCancel, submitting }) {
  const [form, setForm] = useState(() => buildInitialForm(initial, extraFields));
  const [error, setError] = useState(null);

  function handleChange(e) {
    const { name, value } = e.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  }

  async function handleSubmit(e) {
    e.preventDefault();
    setError(null);
    if (!form.title.trim() || !form.location.trim()) {
      setError('Title and location are required');
      return;
    }

    const payload = {
      title: form.title,
      description: form.description,
      location: form.location,
      scheduleText: form.scheduleText,
      priceLabel: form.priceLabel,
      tag: form.tag,
      imageUrls: form.imageUrls,
      latitude: form.latitude === '' ? null : Number(form.latitude),
      longitude: form.longitude === '' ? null : Number(form.longitude),
      status: form.status,
    };
    extraFields.forEach((field) => {
      const raw = form[field.name];
      payload[field.name] = field.type === 'number' ? (raw === '' ? null : Number(raw)) : raw;
    });

    try {
      await onSubmit(payload);
    } catch (err) {
      setError(err.response?.data?.message || 'Failed to save');
    }
  }

  return (
    <form className="product-form" onSubmit={handleSubmit}>
      <Input id="title" name="title" label="Title" value={form.title} onChange={handleChange} />

      <div className="form-field">
        <label htmlFor="description">Description</label>
        <textarea
          id="description"
          name="description"
          className="input"
          rows={3}
          value={form.description}
          onChange={handleChange}
        />
      </div>

      <Input id="location" name="location" label="Location" value={form.location} onChange={handleChange} />

      <div className="product-form-row">
        <Input
          id="scheduleText"
          name="scheduleText"
          label="Schedule"
          placeholder="Every Saturday, 4:00 AM"
          value={form.scheduleText}
          onChange={handleChange}
        />
        <Input id="priceLabel" name="priceLabel" label="Price" placeholder="₹499" value={form.priceLabel} onChange={handleChange} />
      </div>

      <div className="product-form-row">
        <Input id="tag" name="tag" label="Tag" placeholder="Beginner friendly" value={form.tag} onChange={handleChange} />
        <div className="form-field">
          <label htmlFor="status">Status</label>
          <select id="status" name="status" className="input" value={form.status} onChange={handleChange}>
            {STATUSES.map((s) => (
              <option key={s} value={s}>
                {s}
              </option>
            ))}
          </select>
        </div>
      </div>

      <div className="product-form-row">
        <Input id="latitude" name="latitude" type="number" step="any" label="Latitude" value={form.latitude} onChange={handleChange} />
        <Input id="longitude" name="longitude" type="number" step="any" label="Longitude" value={form.longitude} onChange={handleChange} />
      </div>

      {extraFields.filter((f) => f.type !== 'textarea').length > 0 && (
        <div className="product-form-row">
          {extraFields
            .filter((f) => f.type !== 'textarea')
            .map((field) => (
              <Input
                key={field.name}
                id={field.name}
                name={field.name}
                type={field.type === 'number' ? 'number' : 'text'}
                label={field.label}
                value={form[field.name]}
                onChange={handleChange}
              />
            ))}
        </div>
      )}

      {extraFields
        .filter((f) => f.type === 'textarea')
        .map((field) => (
          <div className="form-field" key={field.name}>
            <label htmlFor={field.name}>{field.label}</label>
            <textarea
              id={field.name}
              name={field.name}
              className="input"
              rows={3}
              value={form[field.name]}
              onChange={handleChange}
            />
          </div>
        ))}

      <div className="form-field">
        <label>Images</label>
        <MultiImageUploader
          value={form.imageUrls}
          onChange={(urls) => setForm((prev) => ({ ...prev, imageUrls: urls }))}
          folder={imageFolder}
        />
      </div>

      {error && <p className="form-error">{error}</p>}

      <div className="role-card-footer">
        <Button type="submit" loading={submitting}>
          Save
        </Button>
        <Button type="button" variant="secondary" onClick={onCancel}>
          Cancel
        </Button>
      </div>
    </form>
  );
}
