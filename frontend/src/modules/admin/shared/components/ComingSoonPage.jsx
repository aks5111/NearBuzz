import { Construction } from 'lucide-react';

export default function ComingSoonPage({ title }) {
  return (
    <div className="coming-soon">
      <Construction size={28} aria-hidden="true" />
      <h1>{title}</h1>
      <p>
        The {title.toLowerCase()} table exists in the database — this screen's create/edit/delete UI
        and API are the next thing to build.
      </p>
    </div>
  );
}
