export default function Input({ label, id, error, className = '', ...props }) {
  return (
    <div className="form-field">
      {label && <label htmlFor={id}>{label}</label>}
      <input id={id} className={`input ${error ? 'input-error' : ''} ${className}`.trim()} {...props} />
      {error && <span className="field-error">{error}</span>}
    </div>
  );
}
