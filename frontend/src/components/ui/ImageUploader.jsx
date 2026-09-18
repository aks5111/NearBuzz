import { useState } from 'react';
import { Upload } from 'lucide-react';
import { uploadImage } from '../../api/media.api';

export default function ImageUploader({ value, onChange, folder = 'general' }) {
  const [uploading, setUploading] = useState(false);
  const [error, setError] = useState(null);

  async function handleFile(e) {
    const file = e.target.files?.[0];
    if (!file) return;
    setUploading(true);
    setError(null);
    try {
      const { data } = await uploadImage(file, folder);
      onChange(data?.data?.url);
    } catch (err) {
      setError(err.response?.data?.message || 'Upload failed');
    } finally {
      setUploading(false);
    }
  }

  return (
    <div className="image-uploader">
      {value && <img src={value} alt="" className="image-uploader-preview" />}
      <label className="image-uploader-input">
        <Upload size={14} aria-hidden="true" />
        {uploading ? 'Uploading…' : value ? 'Change image' : 'Upload image'}
        <input type="file" accept="image/png,image/jpeg,image/webp,image/gif" onChange={handleFile} hidden />
      </label>
      {error && <span className="field-error">{error}</span>}
    </div>
  );
}
