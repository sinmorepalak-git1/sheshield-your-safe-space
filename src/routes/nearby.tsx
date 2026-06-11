import { createFileRoute } from "@tanstack/react-router";
import { Navigation, Phone } from "lucide-react";
import { MobileShell, ScreenHeader } from "@/components/mobile-shell";
import { FakeMap } from "./location";

export const Route = createFileRoute("/nearby")({
  component: Nearby,
});

const places = [
  { name: "Cubbon Park Police Station", type: "Police", distance: "0.4 km", eta: "5 min", x: 30, y: 30, color: "bg-blue-600 text-white" },
  { name: "Manipal Hospital", type: "Hospital", distance: "0.9 km", eta: "8 min", x: 70, y: 38, color: "bg-emerald-600 text-white" },
  { name: "Fire Station MG Road", type: "Fire", distance: "1.2 km", eta: "11 min", x: 55, y: 70, color: "bg-orange-500 text-white" },
  { name: "Women's Help Center", type: "NGO", distance: "1.6 km", eta: "14 min", x: 25, y: 65, color: "bg-pink-500 text-white" },
];

const filters = ["All", "Police", "Hospital", "Pharmacy", "Fire", "NGO"];

function Nearby() {
  return (
    <MobileShell>
      <ScreenHeader title="Nearby Help" subtitle="Within 2 km of you" back="/home" />
      <div className="relative h-[40dvh] overflow-hidden">
        <FakeMap markers={places.map(p => ({ x: p.x, y: p.y, label: p.type, color: p.color }))} />
      </div>

      <div className="flex gap-2 overflow-x-auto px-5 py-3 [scrollbar-width:none] [&::-webkit-scrollbar]:hidden">
        {filters.map((f, i) => (
          <button key={f} className={`shrink-0 rounded-full px-4 py-2 text-xs font-semibold ${i === 0 ? "gradient-primary text-primary-foreground" : "bg-secondary text-secondary-foreground"}`}>
            {f}
          </button>
        ))}
      </div>

      <ul className="space-y-3 px-5 pb-4">
        {places.map((p, i) => (
          <li key={p.name} className="flex items-center gap-3 rounded-2xl border border-border bg-card p-3 shadow-soft animate-float-in" style={{ animationDelay: `${i * 0.07}s` }}>
            <div className={`grid h-12 w-12 shrink-0 place-items-center rounded-2xl ${p.color}`}>
              <span className="text-[10px] font-bold uppercase">{p.type.slice(0, 3)}</span>
            </div>
            <div className="min-w-0 flex-1">
              <p className="truncate text-sm font-bold">{p.name}</p>
              <p className="truncate text-xs text-muted-foreground">{p.distance} · {p.eta} away</p>
            </div>
            <div className="flex shrink-0 gap-1">
              <button aria-label="Directions" className="grid h-9 w-9 place-items-center rounded-full bg-primary/15 text-primary"><Navigation className="h-4 w-4"/></button>
              <button aria-label="Call" className="grid h-9 w-9 place-items-center rounded-full bg-success/15 text-success"><Phone className="h-4 w-4"/></button>
            </div>
          </li>
        ))}
      </ul>
    </MobileShell>
  );
}
