import { createFileRoute } from "@tanstack/react-router";
import { Navigation, Share2, Users } from "lucide-react";
import { MobileShell, ScreenHeader } from "@/components/mobile-shell";

export const Route = createFileRoute("/location")({
  component: LocationScreen,
});

function LocationScreen() {
  return (
    <MobileShell>
      <ScreenHeader title="Live Location" subtitle="Sharing with 3 contacts" back="/home" />
      <div className="relative h-[60dvh] overflow-hidden">
        <FakeMap />
        <div className="absolute right-4 top-4 flex flex-col gap-2">
          <button aria-label="Recenter" className="grid h-11 w-11 place-items-center rounded-full bg-card shadow-soft text-primary">
            <Navigation className="h-5 w-5" />
          </button>
          <button aria-label="Share" className="grid h-11 w-11 place-items-center rounded-full bg-card shadow-soft text-primary">
            <Share2 className="h-5 w-5" />
          </button>
        </div>
      </div>
      <div className="-mt-6 rounded-t-3xl border-t border-border bg-card px-5 pt-5 pb-6 shadow-soft">
        <div className="mx-auto mb-4 h-1.5 w-12 rounded-full bg-border" />
        <div className="flex items-center gap-3">
          <span className="relative grid h-10 w-10 place-items-center">
            <span className="absolute inset-0 animate-ping rounded-full bg-success/40" />
            <span className="relative h-3 w-3 rounded-full bg-success" />
          </span>
          <div className="min-w-0 flex-1">
            <p className="text-sm font-bold">Sharing live location</p>
            <p className="truncate text-xs text-muted-foreground">MG Road, Bengaluru · Updated just now</p>
          </div>
          <button className="rounded-full bg-sos px-4 py-2 text-xs font-bold text-sos-foreground">Stop</button>
        </div>
        <div className="mt-4 grid grid-cols-3 gap-3 text-center">
          <Stat label="Accuracy" value="6 m" />
          <Stat label="Speed" value="4 km/h" />
          <Stat label="Battery" value="78%" />
        </div>
        <div className="mt-4 flex items-center gap-2 rounded-xl bg-secondary p-3 text-xs">
          <Users className="h-4 w-4 text-primary" />
          <span className="font-semibold">Mom, Aarav, Neha</span>
          <span className="text-muted-foreground">can see you</span>
        </div>
      </div>
    </MobileShell>
  );
}

function Stat({ label, value }: { label: string; value: string }) {
  return (
    <div className="rounded-xl border border-border bg-background p-3">
      <p className="text-base font-extrabold">{value}</p>
      <p className="text-[10px] uppercase tracking-wider text-muted-foreground">{label}</p>
    </div>
  );
}

export function FakeMap({ markers = [] as { x: number; y: number; label: string; color?: string }[] }) {
  return (
    <div className="absolute inset-0 bg-[oklch(0.94_0.02_270)]">
      <svg className="absolute inset-0 h-full w-full" preserveAspectRatio="none" viewBox="0 0 400 600">
        <defs>
          <pattern id="grid" width="40" height="40" patternUnits="userSpaceOnUse">
            <path d="M 40 0 L 0 0 0 40" fill="none" stroke="oklch(0.88 0.02 270)" strokeWidth="1"/>
          </pattern>
        </defs>
        <rect width="400" height="600" fill="url(#grid)" />
        <path d="M0,180 Q120,160 200,220 T400,260" stroke="oklch(0.80 0.03 270)" strokeWidth="14" fill="none" strokeLinecap="round"/>
        <path d="M60,0 L80,600" stroke="oklch(0.82 0.03 270)" strokeWidth="10" fill="none"/>
        <path d="M280,0 Q320,300 240,600" stroke="oklch(0.82 0.03 270)" strokeWidth="10" fill="none"/>
        <circle cx="120" cy="100" r="40" fill="oklch(0.90 0.06 150)" opacity="0.6"/>
        <circle cx="320" cy="460" r="55" fill="oklch(0.90 0.06 150)" opacity="0.6"/>
        <rect x="180" y="380" width="80" height="60" rx="6" fill="oklch(0.86 0.02 270)"/>
        <rect x="40" y="420" width="60" height="80" rx="6" fill="oklch(0.86 0.02 270)"/>
      </svg>
      {/* user marker */}
      <div className="absolute left-1/2 top-1/2 -translate-x-1/2 -translate-y-1/2">
        <div className="relative grid place-items-center">
          <span className="absolute h-16 w-16 animate-ping rounded-full bg-primary/30" />
          <span className="absolute h-10 w-10 rounded-full bg-primary/40" />
          <span className="relative h-5 w-5 rounded-full border-4 border-white bg-primary shadow-soft" />
        </div>
      </div>
      {markers.map((m, i) => (
        <div key={i} className="absolute -translate-x-1/2 -translate-y-full" style={{ left: `${m.x}%`, top: `${m.y}%` }}>
          <div className={`flex items-center gap-1 rounded-full ${m.color ?? "bg-sos text-sos-foreground"} px-2 py-1 text-[10px] font-bold shadow-soft`}>
            {m.label}
          </div>
        </div>
      ))}
    </div>
  );
}
