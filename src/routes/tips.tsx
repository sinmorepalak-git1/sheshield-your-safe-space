import { createFileRoute } from "@tanstack/react-router";
import { MobileShell, ScreenHeader } from "@/components/mobile-shell";
import { Shield, HeartPulse, Footprints, KeySquare, Car, Phone } from "lucide-react";

export const Route = createFileRoute("/tips")({
  component: Tips,
});

const featured = {
  title: "Trust your instincts",
  desc: "If a situation feels unsafe — it probably is. Move toward people, light, and exits.",
  tag: "Tip of the day",
};

const tips = [
  { icon: Shield, title: "Self defense basics", desc: "Strike vulnerable spots: eyes, throat, knees.", tint: "bg-rose-100 text-rose-600" },
  { icon: Footprints, title: "Walking alone at night", desc: "Stay on well-lit streets, share live location.", tint: "bg-fuchsia-100 text-fuchsia-600" },
  { icon: Car, title: "Riding a cab safely", desc: "Verify number plate & share trip with family.", tint: "bg-purple-100 text-purple-600" },
  { icon: KeySquare, title: "Public transport", desc: "Sit near the driver or in crowded compartments.", tint: "bg-pink-100 text-pink-600" },
  { icon: HeartPulse, title: "Stay calm in panic", desc: "Breathe 4-7-8 to think clearly and respond.", tint: "bg-violet-100 text-violet-600" },
  { icon: Phone, title: "Know the helplines", desc: "Women: 1091 · Police: 100 · Emergency: 112", tint: "bg-rose-100 text-rose-600" },
];

function Tips() {
  return (
    <MobileShell>
      <ScreenHeader title="Safety Tips" subtitle="Small habits, big difference" back="/home" />
      <div className="px-5 pt-4">
        <div className="rounded-3xl gradient-primary p-5 text-primary-foreground shadow-soft">
          <p className="text-[10px] font-bold uppercase tracking-[0.3em] opacity-90">{featured.tag}</p>
          <h2 className="mt-2 text-xl font-extrabold">{featured.title}</h2>
          <p className="mt-2 text-sm text-white/90">{featured.desc}</p>
          <button className="mt-4 rounded-full bg-white/20 px-4 py-2 text-xs font-bold backdrop-blur">
            Read more
          </button>
        </div>
      </div>

      <h2 className="mt-6 px-5 text-sm font-bold uppercase tracking-wider text-muted-foreground">All tips</h2>
      <div className="mt-3 grid grid-cols-1 gap-3 px-5 pb-4">
        {tips.map((t, i) => {
          const Icon = t.icon;
          return (
            <article key={t.title} className="flex items-start gap-3 rounded-2xl border border-border bg-card p-4 shadow-soft animate-float-in" style={{ animationDelay: `${i * 0.05}s` }}>
              <div className={`grid h-11 w-11 shrink-0 place-items-center rounded-2xl ${t.tint}`}>
                <Icon className="h-5 w-5" />
              </div>
              <div className="min-w-0">
                <h3 className="text-sm font-bold">{t.title}</h3>
                <p className="mt-1 text-xs text-muted-foreground">{t.desc}</p>
              </div>
            </article>
          );
        })}
      </div>
    </MobileShell>
  );
}
