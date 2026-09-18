import { useEffect, useState } from 'react';
import DataTable from '../../../../components/ui/DataTable';
import { fetchAdminUsers } from '../../../../api/admin.api';

const COLUMNS = [
  { key: 'fullName', header: 'Name' },
  { key: 'email', header: 'Email' },
  {
    key: 'role',
    header: 'Role',
    render: (row) => <span className="tag">{row.role.replace('ROLE_', '')}</span>,
  },
  {
    key: 'status',
    header: 'Status',
    render: (row) => (
      <span className={`status-dot status-${row.status.toLowerCase()}`}>{row.status}</span>
    ),
  },
  {
    key: 'createdAt',
    header: 'Joined',
    render: (row) => new Date(row.createdAt).toLocaleDateString(),
  },
];

export default function UserManagementPage() {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    let cancelled = false;
    fetchAdminUsers()
      .then(({ data }) => {
        if (!cancelled) setUsers(data?.data ?? []);
      })
      .catch((err) => {
        if (!cancelled) setError(err.response?.data?.message || 'Failed to load users');
      })
      .finally(() => {
        if (!cancelled) setLoading(false);
      });
    return () => {
      cancelled = true;
    };
  }, []);

  return (
    <div>
      <h1>Users</h1>
      <p className="page-subtitle">Everyone registered on NearBuzz, across every role.</p>
      {loading && <p>Loading…</p>}
      {error && <p className="form-error">{error}</p>}
      {!loading && !error && <DataTable columns={COLUMNS} rows={users} emptyMessage="No users yet." />}
    </div>
  );
}
