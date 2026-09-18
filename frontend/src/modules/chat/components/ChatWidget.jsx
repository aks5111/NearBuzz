import { useEffect, useRef, useState } from 'react';
import { MessageCircle, Send, X } from 'lucide-react';
import { sendChatMessage } from '../../../api/chat.api';

export default function ChatWidget() {
  const [open, setOpen] = useState(false);
  const [messages, setMessages] = useState([
    { role: 'assistant', content: "Hi! I'm the NearBuzz assistant. Ask me about activities or shopping nearby." },
  ]);
  const [input, setInput] = useState('');
  const [loading, setLoading] = useState(false);
  const listRef = useRef(null);

  useEffect(() => {
    if (listRef.current) {
      listRef.current.scrollTop = listRef.current.scrollHeight;
    }
  }, [messages, open]);

  async function handleSend(e) {
    e.preventDefault();
    const text = input.trim();
    if (!text || loading) return;

    const history = messages.slice(-10).map(({ role, content }) => ({ role, content }));
    setMessages((prev) => [...prev, { role: 'user', content: text }]);
    setInput('');
    setLoading(true);

    try {
      const { data } = await sendChatMessage(text, history);
      setMessages((prev) => [...prev, { role: 'assistant', content: data?.data?.reply || "Sorry, I didn't catch that." }]);
    } catch (err) {
      const message = err.response?.data?.message || 'Something went wrong. Please try again.';
      setMessages((prev) => [...prev, { role: 'assistant', content: message }]);
    } finally {
      setLoading(false);
    }
  }

  return (
    <div className="chat-widget">
      {open && (
        <div className="chat-panel">
          <div className="chat-panel-header">
            <span>NearBuzz Assistant</span>
            <button type="button" className="icon-btn" onClick={() => setOpen(false)} aria-label="Close chat">
              <X size={16} />
            </button>
          </div>
          <div className="chat-panel-messages" ref={listRef}>
            {messages.map((msg, i) => (
              <div key={i} className={`chat-bubble chat-bubble-${msg.role}`}>
                {msg.content}
              </div>
            ))}
            {loading && <div className="chat-bubble chat-bubble-assistant chat-bubble-loading">Typing…</div>}
          </div>
          <form className="chat-panel-input" onSubmit={handleSend}>
            <input
              type="text"
              value={input}
              onChange={(e) => setInput(e.target.value)}
              placeholder="Ask something…"
              aria-label="Chat message"
            />
            <button type="submit" className="icon-btn" disabled={loading} aria-label="Send">
              <Send size={16} />
            </button>
          </form>
        </div>
      )}
      <button
        type="button"
        className="chat-toggle-btn"
        onClick={() => setOpen((prev) => !prev)}
        aria-label={open ? 'Close chat' : 'Open chat'}
      >
        {open ? <X size={22} /> : <MessageCircle size={22} />}
      </button>
    </div>
  );
}
