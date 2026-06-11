import { createFileRoute, Link, useNavigate } from "@tanstack/react-router";
import { useEffect, useState } from "react";
import { MapPin, Check, Phone } from "lucide-react";

export const Route = createFileRoute("/sos")({
  component: SOS,
});

const contacts = [
  { name: "Mom", phone: "+91 98765 12345", initial: "M", tint: "bg-rose-200" },
  { name: "Aarav (Brother)", phone: "+91 99887 65432", initial: "A", tint: "bg-fuchsia-200" },
  { name: "Neha (Best Friend)", phone: "+91 90123 45678", initial: "N", tint: "bg-purple-200" },
];

function SOS() {
  const [count, setCount] = useState(5);
  const [active, setActive] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    if (active) return;
    if (count <= 0) {
      setActive(true);
      return;
    }
    const t = setTimeout(() => setCount((c) => c - 1), 1000);
    return () => clearTimeout(t);
  }, [count, active]);

  if (active) {
    return (
      <div className="mx-auto flex min-h-dvh w-full max-w-md flex-col bg-background px-5 py-8">
        <div className="rounded-3xl gradient-sos p-6 text-sos-foreground shadow-sos">
          <p className="text-xs font-bold uppercase tracking-[0.3em] opacity-90">SOS Active</p>
          <h1 className="mt-2 text-3xl font-extrabold">Help is on the way</h1>
          <p className="mt-2 text-sm text-white/90">Your location is being shared live with your trusted contacts and authorities.</p>
        </div>

        <div className="mt-5 flex items-center gap-3 rounded-2xl border border-border bg-card p-4">
          <span className="relative grid h-10 w-10 place-items-center">
            <span className="absolute inset-0 animate-ping rounded-full bg-success/40" />
            <span className="relative grid h-3 w-3 place-items-center rounded-full bg-success" />
          </span>
          <div className="min-w-0">
            <p className="text-sm font-semibold">Live location sharing</p>
            <p className="truncate text-xs text-muted-foreground">MG Road, Bengaluru · accuracy 6 m</p>
          </div>
        </div>

        <h2 className="mt-6 text-sm font-bold uppercase tracking-wider text-muted-foreground">Notifying</h2>
        <ul className="mt-3 space-y-2">
          {contacts.map((c, i) => (
            <li key={c.name} className="flex items-center gap-3 rounded-2xl border border-border bg-card p-3 animate-float-in" style={{ animationDelay: `${i * 0.15}s` }}>
              <div className={`grid h-10 w-10 place-items-center rounded-full ${c.tint} font-bold text-foreground`}>{c.initial}</div>
              <div className="min-w-0 flex-1">
                <p className="truncate text-sm font-semibold">{c.name}</p>
                <p className="truncate text-xs text-muted-foreground">{c.phone}</p>
              </div>
              <span className="flex items-center gap-1 text-xs font-semibold text-success">
                <Check className="h-4 w-4" /> Notified
              </span>
            </li>
          ))}
        </ul>

        <div className="mt-auto grid grid-cols-2 gap-3 pt-8">
          <a href="tel:112" className="flex h-14 items-center justify-center gap-2 rounded-2xl bg-sos text-sos-foreground font-semibold shadow-sos">
            <Phone className="h-5 w-5" /> Call 112
          </a>
          <button onClick={() => navigate({ to: "/home" })} className="h-14 rounded-2xl border border-border bg-card font-semibold">
            I'm Safe Now
          </button>
        </div>
      </div>
    );
  }

  return (
    <div className="mx-auto flex min-h-dvh w-full max-w-md flex-col items-center justify-center gradient-sos px-6 text-sos-foreground">
      <p className="text-xs font-bold uppercase tracking-[0.4em] opacity-90">Activating SOS in</p>
      <div className="relative mt-8 grid h-64 w-64 place-items-center">
        <span className="absolute inset-0 animate-ripple rounded-full bg-white/30" />
        <span className="absolute inset-6 animate-ripple rounded-full bg-white/20" style={{ animationDelay: "0.7s" }} />
        <div className="relative grid h-52 w-52 place-items-center rounded-full bg-white/15 backdrop-blur">
          <span className="text-[8rem] font-black leading-none">{count}</span>
        </div>
      </div>
      <p className="mt-6 max-w-xs text-center text-sm text-white/85">
        <MapPin className="mr-1 inline h-4 w-4" /> Preparing live location & alert message
      </p>
      <Link
        to="/home"
        className="mt-10 flex h-14 w-full max-w-xs items-center justify-center rounded-2xl bg-white text-sos font-bold shadow-2xl active:scale-[0.98]"
      >
        Cancel
      </Link>
    </div>
  );
}
