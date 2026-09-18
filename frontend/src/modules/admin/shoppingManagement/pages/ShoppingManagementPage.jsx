import { useEffect, useState } from 'react';
import { Pencil, Plus, Trash2 } from 'lucide-react';
import DataTable from '../../../../components/ui/DataTable';
import Button from '../../../../components/ui/Button';
import Card from '../../../../components/ui/Card';
import ProductForm from '../components/ProductForm';
import {
  fetchAdminProducts,
  createAdminProduct,
  updateAdminProduct,
  deleteAdminProduct,
  fetchShoppingCategories,
} from '../../../../api/shopping.api';

export default function ShoppingManagementPage() {
  const [products, setProducts] = useState([]);
  const [categories, setCategories] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [editing, setEditing] = useState(null);
  const [showForm, setShowForm] = useState(false);
  const [submitting, setSubmitting] = useState(false);

  function load() {
    setLoading(true);
    Promise.all([fetchAdminProducts(), fetchShoppingCategories()])
      .then(([productsRes, categoriesRes]) => {
        setProducts(productsRes.data?.data ?? []);
        setCategories(categoriesRes.data?.data ?? []);
      })
      .catch((err) => setError(err.response?.data?.message || 'Failed to load products'))
      .finally(() => setLoading(false));
  }

  useEffect(load, []);

  function openCreate() {
    setEditing(null);
    setShowForm(true);
  }

  function openEdit(product) {
    setEditing(product);
    setShowForm(true);
  }

  async function handleSubmit(payload) {
    setSubmitting(true);
    try {
      if (editing) {
        await updateAdminProduct(editing.id, payload);
      } else {
        await createAdminProduct(payload);
      }
      setShowForm(false);
      load();
    } finally {
      setSubmitting(false);
    }
  }

  async function handleDelete(product) {
    if (!window.confirm(`Delete "${product.title}"?`)) return;
    await deleteAdminProduct(product.id);
    load();
  }

  const columns = [
    {
      key: 'image',
      header: '',
      render: (row) => (row.imageUrl ? <img src={row.imageUrl} alt="" className="table-thumb" /> : null),
    },
    { key: 'title', header: 'Title' },
    { key: 'category', header: 'Category', render: (row) => row.category.name },
    { key: 'priceLabel', header: 'Price' },
    { key: 'stockQuantity', header: 'Stock' },
    { key: 'status', header: 'Status', render: (row) => <span className="tag">{row.status}</span> },
    {
      key: 'actions',
      header: '',
      render: (row) => (
        <div className="table-actions">
          <button type="button" className="icon-btn" onClick={() => openEdit(row)} aria-label="Edit">
            <Pencil size={15} />
          </button>
          <button type="button" className="icon-btn" onClick={() => handleDelete(row)} aria-label="Delete">
            <Trash2 size={15} />
          </button>
        </div>
      ),
    },
  ];

  return (
    <div>
      <div className="page-header-row">
        <div>
          <h1>Shopping</h1>
          <p className="page-subtitle">Manage the product catalog shown on the public shopping page.</p>
        </div>
        <Button onClick={openCreate}>
          <Plus size={16} style={{ marginRight: 6 }} />
          Add product
        </Button>
      </div>

      {showForm && (
        <Card className="product-form-card">
          <h3>{editing ? 'Edit product' : 'New product'}</h3>
          <ProductForm
            initial={editing}
            categories={categories}
            onSubmit={handleSubmit}
            onCancel={() => setShowForm(false)}
            submitting={submitting}
          />
        </Card>
      )}

      {loading && <p>Loading…</p>}
      {error && <p className="form-error">{error}</p>}
      {!loading && !error && <DataTable columns={columns} rows={products} emptyMessage="No products yet." />}
    </div>
  );
}
