export default function Button({ children, loading, variant = 'primary', className = '', ...props }) {
  return (
    <button
      className={`btn btn-${variant} ${className}`.trim()}
      disabled={loading || props.disabled}
      {...props}
    >
      {loading ? 'Please wait…' : children}
    </button>
  );
}
