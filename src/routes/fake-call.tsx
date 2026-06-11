import { createFileRoute } from "@tanstack/react-router";
import { useState } from "react";
import { Phone, PhoneOff, Video, MessageSquare } from "lucide-react";
import { MobileShell, ScreenHeader } from "@/components/mobile-shell";

export const Route = createFileRoute("/fake-call")({
  component: FakeCall,
});

const callers = [
  { name: "Dad", sub: "Mobile", tint: "from-rose-300 to-pink-400" },
  { name: "Boss", sub: "Work", tint: "from-violet-300 to-purple-400" },
  { name: "Mom", sub: "Home", tint: "from-fuchsia-300 to-pink-400" },
  { name: "Unknown", sub: "Private", tint: "from-slate-300 to-slate-500" },
];
const delays = ["5 sec", "30 sec", "1 min", "5 min"];

function FakeCall() {
  const [caller, setCaller] = useState(0);
  const [delay, setDelay] = useState(0);
  const [preview, setPreview] = useState(false);

  if (preview) return <IncomingCall name={callers[caller].name} tint={callers[caller].tint} onEnd={() => setPreview(false)} />;

  return (
    <MobileShell>
      <ScreenHeader title="Fake Call" subtitle="Stage an escape route" back="/home" />
      <div className="space-y-6 px-5 pt-4">
        <section>
          <h2 className="mb-3 text-sm font-bold uppercase tracking-wider text-muted-foreground">Caller</h2>
          <div className="grid grid-cols-2 gap-3">
            {callers.map((c, i) => (
              <button
                key={c.name}
                onClick={() => setCaller(i)}
                className={`flex items-center gap-3 rounded-2xl border p-3 text-left transition-all ${caller === i ? "border-primary bg-primary/5" : "border-border bg-card"}`}
              >
                <div className={`grid h-11 w-11 place-items-center rounded-full bg-gradient-to-br ${c.tint} text-sm font-bold text-white`}>
                  {c.name[0]}
                </div>
                <div className="min-w-0">
                  <p className="truncate text-sm font-bold">{c.name}</p>
                  <p className="truncate text-[11px] text-muted-foreground">{c.sub}</p>
                </div>
              </button>
            ))}
          </div>
        </section>

        <section>
          <h2 className="mb-3 text-sm font-bold uppercase tracking-wider text-muted-foreground">Delay</h2>
          <div className="grid grid-cols-4 gap-2">
            {delays.map((d, i) => (
              <button key={d} onClick={() => setDelay(i)} className={`rounded-xl border py-3 text-xs font-bold ${delay === i ? "border-primary bg-primary/10 text-primary" : "border-border bg-card text-foreground"}`}>
                {d}
              </button>
            ))}
          </div>
        </section>

        <button onClick={() => setPreview(true)} className="flex h-14 w-full items-center justify-center rounded-2xl gradient-primary font-semibold text-primary-foreground shadow-soft">
          Schedule fake call
        </button>

        <p className="text-center text-xs text-muted-foreground">
          The call will look real with vibration & ringtone. Tap to preview it now.
        </p>
      </div>
    </MobileShell>
  );
}

function IncomingCall({ name, tint, onEnd }: { name: string; tint: string; onEnd: () => void }) {
  return (
    <div className="mx-auto flex min-h-dvh w-full max-w-md flex-col items-center justify-between gradient-soft px-6 py-12 text-foreground">
      <div className="flex flex-col items-center pt-8">
        <p className="text-xs font-semibold uppercase tracking-[0.3em] text-muted-foreground">Incoming call</p>
        <div className="relative mt-8">
          <span className="absolute inset-0 animate-ripple rounded-full bg-primary/30" />
          <div className={`relative grid h-36 w-36 place-items-center rounded-full bg-gradient-to-br ${tint} text-5xl font-bold text-white shadow-soft`}>
            {name[0]}
          </div>
        </div>
        <h1 className="mt-8 text-3xl font-extrabold">{name}</h1>
        <p className="mt-1 text-sm text-muted-foreground">mobile · India</p>
      </div>

      <div className="flex w-full items-center justify-around pb-6">
        <button aria-label="Message" className="grid h-12 w-12 place-items-center rounded-full bg-card shadow-soft text-foreground">
          <MessageSquare className="h-5 w-5" />
        </button>
        <button onClick={onEnd} aria-label="Decline" className="grid h-16 w-16 place-items-center rounded-full bg-sos text-sos-foreground shadow-sos">
          <PhoneOff className="h-7 w-7" />
        </button>
        <button onClick={onEnd} aria-label="Accept" className="grid h-16 w-16 animate-sos-pulse place-items-center rounded-full bg-success text-success-foreground">
          <Phone className="h-7 w-7" />
        </button>
        <button aria-label="Video" className="grid h-12 w-12 place-items-center rounded-full bg-card shadow-soft text-foreground">
          <Video className="h-5 w-5" />
        </button>
      </div>
    </div>
  );
}
