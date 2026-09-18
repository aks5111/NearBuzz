import { useEffect, useState } from 'react';
import { Pencil, Plus, Trash2 } from 'lucide-react';
import DataTable from '../../../../components/ui/DataTable';
import Button from '../../../../components/ui/Button';
import Card from '../../../../components/ui/Card';
import ListingForm from './ListingForm';

export default function ListingManagementPage({
  title,
  subtitle,
  imageFolder,
  extraFields = [],
  extraColumns = [],
  api,
}) {
  const [items, setItems] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [editing, setEditing] = useState(null);
  const [showForm, setShowForm] = useState(false);
  const [submitting, setSubmitting] = useState(false);

  function load() {
    setLoading(true);
    api
      .list()
      .then(({ data }) => setItems(data?.data ?? []))
      .catch((err) => setError(err.response?.data?.message || `Failed to load ${title.toLowerCase()}`))
      .finally(() => setLoading(false));
  }

  useEffect(load, []);

  function openCreate() {
    setEditing(null);
    setShowForm(true);
  }

  function openEdit(item) {
    setEditing(item);
    setShowForm(true);
  }

  async function handleSubmit(payload) {
    setSubmitting(true);
    try {
      if (editing) {
        await api.update(editing.id, payload);
      } else {
        await api.create(payload);
      }
      setShowForm(false);
      load();
    } finally {
      setSubmitting(false);
    }
  }

  async function handleDelete(item) {
    if (!window.confirm(`Delete "${item.title}"?`)) return;
    await api.remove(item.id);
    load();
  }

  const columns = [
    {
      key: 'image',
      header: '',
      render: (row) => (row.imageUrl ? <img src={row.imageUrl} alt="" className="table-thumb" /> : null),
    },
    { key: 'title', header: 'Title' },
    { key: 'location', header: 'Location' },
    { key: 'priceLabel', header: 'Price' },
    ...extraColumns,
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
          <h1>{title}</h1>
          <p className="page-subtitle">{subtitle}</p>
        </div>
        <Button onClick={openCreate}>
          <Plus size={16} style={{ marginRight: 6 }} />
          Add {title.toLowerCase()}
        </Button>
      </div>

      {showForm && (
        <Card className="product-form-card">
          <h3>{editing ? `Edit ${title.toLowerCase()}` : `New ${title.toLowerCase()}`}</h3>
          <ListingForm
            initial={editing}
            extraFields={extraFields}
            imageFolder={imageFolder}
            onSubmit={handleSubmit}
            onCancel={() => setShowForm(false)}
            submitting={submitting}
          />
        </Card>
      )}

      {loading && <p>Loading…</p>}
      {error && <p className="form-error">{error}</p>}
      {!loading && !error && (
        <DataTable columns={columns} rows={items} emptyMessage={`No ${title.toLowerCase()} listings yet.`} />
      )}
    </div>
  );
}
