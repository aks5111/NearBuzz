import { useEffect, useMemo, useState } from 'react';
import { ShieldCheck } from 'lucide-react';
import Button from '../../../../components/ui/Button';
import { useAuth } from '../../../auth/hooks/useAuth';
import { ROLES } from '../../../../constants';
import { fetchRoles, fetchPermissions, updateRolePermissions } from '../../../../api/rbac.api';

function groupByModule(permissions) {
  const groups = new Map();
  for (const permission of permissions) {
    if (!groups.has(permission.module)) groups.set(permission.module, []);
    groups.get(permission.module).push(permission);
  }
  return groups;
}

function RoleCard({ role, permissionsByModule, canEdit, onSave }) {
  const [selected, setSelected] = useState(new Set(role.permissionIds));
  const [saving, setSaving] = useState(false);
  const [savedMessage, setSavedMessage] = useState(null);
  const isSuperAdmin = role.name === ROLES.SUPER_ADMIN;
  const locked = isSuperAdmin || !canEdit;

  function toggle(permissionId) {
    if (locked) return;
    setSelected((prev) => {
      const next = new Set(prev);
      next.has(permissionId) ? next.delete(permissionId) : next.add(permissionId);
      return next;
    });
    setSavedMessage(null);
  }

  async function handleSave() {
    setSaving(true);
    setSavedMessage(null);
    try {
      await onSave(role.id, Array.from(selected));
      setSavedMessage('Saved');
    } catch (err) {
      setSavedMessage(err.response?.data?.message || 'Failed to save');
    } finally {
      setSaving(false);
    }
  }

  return (
    <div className="role-card">
      <div className="role-card-header">
        <div>
          <h3>{role.name.replace('ROLE_', '')}</h3>
          <p>{role.description}</p>
        </div>
        {isSuperAdmin && (
          <span className="tag role-locked-tag">
            <ShieldCheck size={12} aria-hidden="true" /> Always full access
          </span>
        )}
      </div>

      {Array.from(permissionsByModule.entries()).map(([module, permissions]) => (
        <div className="role-module-group" key={module}>
          <p className="role-module-label">{module}</p>
          <div className="role-permission-grid">
            {permissions.map((permission) => (
              <label key={permission.id} className={`role-permission-checkbox ${locked ? 'is-locked' : ''}`}>
                <input
                  type="checkbox"
                  checked={selected.has(permission.id)}
                  onChange={() => toggle(permission.id)}
                  disabled={locked}
                />
                <span>{permission.description}</span>
              </label>
            ))}
          </div>
        </div>
      ))}

      {!locked && (
        <div className="role-card-footer">
          <Button onClick={handleSave} loading={saving}>
            Save changes
          </Button>
          {savedMessage && <span className="role-save-message">{savedMessage}</span>}
        </div>
      )}
    </div>
  );
}

export default function RolesPermissionsPage() {
  const { role: currentUserRole } = useAuth();
  const [roles, setRoles] = useState([]);
  const [permissions, setPermissions] = useState([]);
  const [selectedRoleId, setSelectedRoleId] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    let cancelled = false;
    Promise.all([fetchRoles(), fetchPermissions()])
      .then(([rolesRes, permissionsRes]) => {
        if (cancelled) return;
        const roleList = rolesRes.data?.data ?? [];
        setRoles(roleList);
        setPermissions(permissionsRes.data?.data ?? []);
        setSelectedRoleId(roleList[0]?.id ?? null);
      })
      .catch((err) => {
        if (!cancelled) setError(err.response?.data?.message || 'Failed to load roles');
      })
      .finally(() => {
        if (!cancelled) setLoading(false);
      });
    return () => {
      cancelled = true;
    };
  }, []);

  const permissionsByModule = useMemo(() => groupByModule(permissions), [permissions]);
  const canEdit = currentUserRole === ROLES.SUPER_ADMIN;
  const selectedRole = roles.find((r) => r.id === selectedRoleId);

  async function handleSave(roleId, permissionIds) {
    await updateRolePermissions(roleId, permissionIds);
  }

  return (
    <div>
      <h1>Roles &amp; Permissions</h1>
      <p className="page-subtitle">
        {canEdit
          ? 'Pick a role to see and edit what it can do across the admin panel.'
          : "Only a super admin can change these. You're viewing them read-only."}
      </p>

      {loading && <p>Loading…</p>}
      {error && <p className="form-error">{error}</p>}

      {!loading && !error && roles.length > 0 && (
        <>
          <div className="form-field role-select-field">
            <label htmlFor="roleSelect">Role</label>
            <select
              id="roleSelect"
              className="input"
              value={selectedRoleId ?? ''}
              onChange={(e) => setSelectedRoleId(Number(e.target.value))}
            >
              {roles.map((role) => (
                <option key={role.id} value={role.id}>
                  {role.name.replace('ROLE_', '')}
                </option>
              ))}
            </select>
          </div>

          {selectedRole && (
            <RoleCard
              key={selectedRole.id}
              role={selectedRole}
              permissionsByModule={permissionsByModule}
              canEdit={canEdit}
              onSave={handleSave}
            />
          )}
        </>
      )}
    </div>
  );
}
