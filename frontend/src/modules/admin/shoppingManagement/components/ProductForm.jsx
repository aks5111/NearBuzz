import { useState } from 'react';
import Button from '../../../../components/ui/Button';
import Input from '../../../../components/ui/Input';
import MultiImageUploader from '../../../../components/ui/MultiImageUploader';

const STATUSES = ['DRAFT', 'PUBLISHED', 'ARCHIVED'];

export default function ProductForm({ initial, categories, onSubmit, onCancel, submitting }) {
  const [form, setForm] = useState({
    categoryId: initial?.category?.id ?? categories[0]?.id ?? '',
    title: initial?.title ?? '',
    description: initial?.description ?? '',
    priceLabel: initial?.priceLabel ?? '',
    imageUrls: initial?.imageUrls ?? [],
    stockQuantity: initial?.stockQuantity ?? 0,
    location: initial?.location ?? '',
    storeName: initial?.storeName ?? '',
    storeContactName: initial?.storeContactName ?? '',
    storePhone: initial?.storePhone ?? '',
    storeEmail: initial?.storeEmail ?? '',
    storePhotoUrl: initial?.storePhotoUrl ?? '',
    status: initial?.status ?? 'PUBLISHED',
  });
  const [error, setError] = useState(null);

  function handleChange(e) {
    const { name, value } = e.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  }

  async function handleSubmit(e) {
    e.preventDefault();
    setError(null);
    if (!form.categoryId || !form.title.trim() || !form.priceLabel.trim()) {
      setError('Category, title and price are required');
      return;
    }
    try {
      await onSubmit({
        categoryId: Number(form.categoryId),
        title: form.title,
        description: form.description,
        priceLabel: form.priceLabel,
        imageUrls: form.imageUrls,
        stockQuantity: Number(form.stockQuantity),
        location: form.location,
        storeName: form.storeName,
        storeContactName: form.storeContactName,
        storePhone: form.storePhone,
        storeEmail: form.storeEmail,
        storePhotoUrl: form.storePhotoUrl,
        status: form.status,
      });
    } catch (err) {
      setError(err.response?.data?.message || 'Failed to save product');
    }
  }

  return (
    <form className="product-form" onSubmit={handleSubmit}>
      <div className="form-field">
        <label htmlFor="categoryId">Category</label>
        <select id="categoryId" name="categoryId" className="input" value={form.categoryId} onChange={handleChange}>
          {categories.map((c) => (
            <option key={c.id} value={c.id}>
              {c.name}
            </option>
          ))}
        </select>
      </div>

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

      <div className="product-form-row">
        <Input id="priceLabel" name="priceLabel" label="Price" placeholder="₹499" value={form.priceLabel} onChange={handleChange} />
        <Input
          id="stockQuantity"
          name="stockQuantity"
          type="number"
          min="0"
          label="Stock quantity"
          value={form.stockQuantity}
          onChange={handleChange}
        />
      </div>

      <Input id="location" name="location" label="Store area / location" placeholder="Koramangala, Bangalore" value={form.location} onChange={handleChange} />

      <div className="product-form-row">
        <Input id="storeName" name="storeName" label="Store name" value={form.storeName} onChange={handleChange} />
        <Input id="storeContactName" name="storeContactName" label="Store contact person" value={form.storeContactName} onChange={handleChange} />
      </div>

      <div className="product-form-row">
        <Input id="storePhone" name="storePhone" label="Store phone" value={form.storePhone} onChange={handleChange} />
        <Input id="storeEmail" name="storeEmail" label="Store email" value={form.storeEmail} onChange={handleChange} />
      </div>

      <Input id="storePhotoUrl" name="storePhotoUrl" label="Store contact photo URL" value={form.storePhotoUrl} onChange={handleChange} />

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

      <div className="form-field">
        <label>Images</label>
        <MultiImageUploader
          value={form.imageUrls}
          onChange={(urls) => setForm((prev) => ({ ...prev, imageUrls: urls }))}
          folder="shopping"
        />
      </div>

      {error && <p className="form-error">{error}</p>}

      <div className="role-card-footer">
        <Button type="submit" loading={submitting}>
          Save product
        </Button>
        <Button type="button" variant="secondary" onClick={onCancel}>
          Cancel
        </Button>
      </div>
    </form>
  );
}
