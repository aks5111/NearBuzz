import { useState } from 'react';
import { Upload, X } from 'lucide-react';
import { uploadImage } from '../../api/media.api';

export default function MultiImageUploader({ value = [], onChange, folder = 'general' }) {
  const [uploading, setUploading] = useState(false);
  const [error, setError] = useState(null);

  async function handleFiles(e) {
    const files = Array.from(e.target.files || []);
    if (files.length === 0) return;
    setUploading(true);
    setError(null);
    try {
      const uploaded = await Promise.all(files.map((file) => uploadImage(file, folder)));
      const urls = uploaded.map((res) => res.data?.data?.url).filter(Boolean);
      onChange([...value, ...urls]);
    } catch (err) {
      setError(err.response?.data?.message || 'Upload failed');
    } finally {
      setUploading(false);
      e.target.value = '';
    }
  }

  function removeAt(index) {
    onChange(value.filter((_, i) => i !== index));
  }

  return (
    <div className="multi-image-uploader">
      {value.length > 0 && (
        <div className="multi-image-grid">
          {value.map((url, index) => (
            <div className="multi-image-thumb" key={url + index}>
              <img src={url} alt="" />
              {index === 0 && <span className="multi-image-cover-tag">Cover</span>}
              <button
                type="button"
                className="multi-image-remove"
                onClick={() => removeAt(index)}
                aria-label="Remove image"
              >
                <X size={12} />
              </button>
            </div>
          ))}
        </div>
      )}

      <label className="image-uploader-input">
        <Upload size={14} aria-hidden="true" />
        {uploading ? 'Uploading…' : 'Add images'}
        <input
          type="file"
          accept="image/png,image/jpeg,image/webp,image/gif"
          multiple
          onChange={handleFiles}
          hidden
        />
      </label>
      {error && <span className="field-error">{error}</span>}
    </div>
  );
}
