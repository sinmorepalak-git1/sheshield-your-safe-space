import { createFileRoute } from "@tanstack/react-router";
import { useState } from "react";
import { Mic, Smartphone } from "lucide-react";
import { MobileShell, ScreenHeader } from "@/components/mobile-shell";

export const Route = createFileRoute("/voice-shake")({
  component: VoiceShake,
});

function VoiceShake() {
  const [voice, setVoice] = useState(true);
  const [shake, setShake] = useState(true);
  const [voiceSens, setVoiceSens] = useState(70);
  const [shakeSens, setShakeSens] = useState(55);

  return (
    <MobileShell>
      <ScreenHeader title="Voice & Shake" subtitle="Hands-free SOS triggers" back="/home" />
      <div className="space-y-5 px-5 pt-4">
        <Section
          icon={<Mic className="h-5 w-5" />}
          title="Voice command"
          desc="Say “Help me SheShield” to trigger SOS even when your phone is locked."
          enabled={voice}
          onToggle={() => setVoice(!voice)}
          value={voiceSens}
          onChange={setVoiceSens}
          accent="from-rose-300 to-pink-400"
          phrase="Help me SheShield"
        />
        <Section
          icon={<Smartphone className="h-5 w-5" />}
          title="Shake detection"
          desc="Shake your phone vigorously 3 times to start the SOS countdown."
          enabled={shake}
          onToggle={() => setShake(!shake)}
          value={shakeSens}
          onChange={setShakeSens}
          accent="from-violet-300 to-purple-400"
        />
        <button className="flex h-14 w-full items-center justify-center rounded-2xl gradient-primary font-semibold text-primary-foreground shadow-soft">
          Test triggers
        </button>
      </div>
    </MobileShell>
  );
}

function Section({
  icon, title, desc, enabled, onToggle, value, onChange, accent, phrase,
}: { icon: React.ReactNode; title: string; desc: string; enabled: boolean; onToggle: () => void; value: number; onChange: (v: number) => void; accent: string; phrase?: string }) {
  return (
    <div className="rounded-3xl border border-border bg-card p-5 shadow-soft">
      <div className="flex items-start gap-3">
        <div className={`grid h-11 w-11 shrink-0 place-items-center rounded-2xl bg-gradient-to-br ${accent} text-white`}>{icon}</div>
        <div className="min-w-0 flex-1">
          <p className="font-bold">{title}</p>
          <p className="mt-1 text-xs text-muted-foreground">{desc}</p>
        </div>
        <Toggle on={enabled} onChange={onToggle} />
      </div>
      {phrase && (
        <div className="mt-4 rounded-xl bg-secondary px-3 py-2 text-xs">
          Wake phrase: <span className="font-bold text-foreground">“{phrase}”</span>
        </div>
      )}
      <div className="mt-4">
        <div className="mb-2 flex items-center justify-between text-xs">
          <span className="font-semibold text-muted-foreground">Sensitivity</span>
          <span className="font-bold">{value}%</span>
        </div>
        <input
          type="range" min={0} max={100} value={value} onChange={(e) => onChange(Number(e.target.value))} disabled={!enabled}
          className="h-2 w-full accent-primary disabled:opacity-50"
        />
        <div className="mt-1 flex justify-between text-[10px] font-semibold uppercase tracking-wider text-muted-foreground">
          <span>Low</span><span>High</span>
        </div>
      </div>
    </div>
  );
}

function Toggle({ on, onChange }: { on: boolean; onChange: () => void }) {
  return (
    <button onClick={onChange} aria-pressed={on} className={`relative h-7 w-12 shrink-0 rounded-full transition-colors ${on ? "bg-primary" : "bg-muted"}`}>
      <span className={`absolute top-0.5 h-6 w-6 rounded-full bg-white shadow transition-all ${on ? "left-[22px]" : "left-0.5"}`} />
    </button>
  );
}
