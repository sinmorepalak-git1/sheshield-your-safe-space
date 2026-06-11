import { createFileRoute, Link, useNavigate } from "@tanstack/react-router";
import { useState } from "react";
import { Bell, MapPin, LifeBuoy } from "lucide-react";

export const Route = createFileRoute("/onboarding")({
  component: Onboarding,
});

const slides = [
  {
    icon: Bell,
    title: "Instant SOS Alerts",
    desc: "One tap sends your location and an emergency alert to your trusted circle in seconds.",
    tint: "from-rose-200 to-pink-300",
  },
  {
    icon: MapPin,
    title: "Live Location Sharing",
    desc: "Share your real-time location with family and friends so you're never truly alone.",
    tint: "from-fuchsia-200 to-purple-300",
  },
  {
    icon: LifeBuoy,
    title: "Emergency Assistance",
    desc: "Quick access to nearby police, hospitals, fake calls, and self-defense tips.",
    tint: "from-violet-200 to-pink-300",
  },
];

function Onboarding() {
  const [i, setI] = useState(0);
  const navigate = useNavigate();
  const slide = slides[i];
  const Icon = slide.icon;
  const last = i === slides.length - 1;

  return (
    <div className="mx-auto flex min-h-dvh w-full max-w-md flex-col bg-background px-6 py-6">
      <div className="flex justify-end">
        <Link to="/login" className="text-sm font-semibold text-muted-foreground hover:text-foreground">
          Skip
        </Link>
      </div>

      <div key={i} className="flex flex-1 flex-col items-center justify-center text-center animate-float-in">
        <div className={`grid h-56 w-56 place-items-center rounded-[3rem] bg-gradient-to-br ${slide.tint} shadow-soft`}>
          <Icon className="h-24 w-24 text-white drop-shadow-md" strokeWidth={1.8} />
        </div>
        <h2 className="mt-10 text-2xl font-extrabold tracking-tight">{slide.title}</h2>
        <p className="mt-3 max-w-xs text-sm leading-relaxed text-muted-foreground">{slide.desc}</p>
      </div>

      <div className="mb-6 flex justify-center gap-2">
        {slides.map((_, idx) => (
          <span
            key={idx}
            className={`h-2 rounded-full transition-all ${idx === i ? "w-8 bg-primary" : "w-2 bg-border"}`}
          />
        ))}
      </div>

      <button
        onClick={() => (last ? navigate({ to: "/login" }) : setI(i + 1))}
        className="h-14 w-full rounded-2xl gradient-primary text-base font-semibold text-primary-foreground shadow-soft transition-transform active:scale-[0.98]"
      >
        {last ? "Get Started" : "Next"}
      </button>
    </div>
  );
}
