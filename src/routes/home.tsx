import { createFileRoute, Link } from "@tanstack/react-router";
import { Users, MapPin, Hospital, PhoneCall, BookOpen, ShieldCheck, Bell, Mic } from "lucide-react";
import { MobileShell } from "@/components/mobile-shell";

export const Route = createFileRoute("/home")({
  component: Home,
});

const quickActions = [
  { to: "/contacts", label: "Emergency Contacts", icon: Users, tint: "bg-rose-100 text-rose-600" },
  { to: "/location", label: "Live Location", icon: MapPin, tint: "bg-fuchsia-100 text-fuchsia-600" },
  { to: "/nearby", label: "Nearby Help", icon: Hospital, tint: "bg-purple-100 text-purple-600" },
  { to: "/fake-call", label: "Fake Call", icon: PhoneCall, tint: "bg-pink-100 text-pink-600" },
  { to: "/tips", label: "Safety Tips", icon: BookOpen, tint: "bg-violet-100 text-violet-600" },
  { to: "/voice-shake", label: "Voice & Shake", icon: Mic, tint: "bg-rose-100 text-rose-600" },
] as const;

function Home() {
  return (
    <MobileShell>
      <header className="gradient-soft px-5 pb-6 pt-8">
        <div className="flex items-center justify-between">
          <div>
            <p className="text-xs font-medium text-muted-foreground">Good evening</p>
            <h1 className="text-xl font-extrabold tracking-tight">Priya 👋</h1>
          </div>
          <Link to="/profile" aria-label="Profile" className="grid h-11 w-11 place-items-center overflow-hidden rounded-full bg-gradient-to-br from-pink-300 to-purple-400 text-white font-bold shadow-soft">
            P
          </Link>
        </div>

        <div className="mt-5 flex items-center gap-3 rounded-2xl border border-success/30 bg-success/10 p-3">
          <div className="grid h-10 w-10 shrink-0 place-items-center rounded-full bg-success text-success-foreground">
            <ShieldCheck className="h-5 w-5" />
          </div>
          <div className="min-w-0">
            <p className="truncate text-sm font-semibold">You're in a safe zone</p>
            <p className="truncate text-xs text-muted-foreground">3 contacts watching · Location ON</p>
          </div>
        </div>
      </header>

      <section className="-mt-2 flex flex-col items-center px-5 pb-2 pt-6">
        <p className="text-xs font-semibold uppercase tracking-[0.2em] text-muted-foreground">Hold to activate</p>
        <Link
          to="/sos"
          aria-label="Activate SOS"
          className="relative mt-4 grid h-52 w-52 place-items-center"
        >
          <span className="absolute inset-0 animate-ripple rounded-full bg-sos/40" />
          <span className="absolute inset-3 animate-ripple rounded-full bg-sos/30" style={{ animationDelay: "0.8s" }} />
          <span className="relative grid h-44 w-44 place-items-center rounded-full gradient-sos text-sos-foreground shadow-sos animate-sos-pulse">
            <div className="text-center">
              <p className="text-5xl font-black tracking-tight">SOS</p>
              <p className="mt-1 text-[10px] font-semibold uppercase tracking-[0.3em] opacity-90">Emergency</p>
            </div>
          </span>
        </Link>
        <p className="mt-5 text-center text-xs text-muted-foreground">
          Tap once to start a 5-second countdown. Your trusted contacts will be alerted.
        </p>
      </section>

      <section className="px-5 pt-6">
        <div className="mb-3 flex items-center justify-between">
          <h2 className="text-base font-bold">Quick actions</h2>
          <button className="text-xs font-semibold text-primary">Customize</button>
        </div>
        <div className="grid grid-cols-2 gap-3">
          {quickActions.map(({ to, label, icon: Icon, tint }) => (
            <Link
              key={to}
              to={to}
              className="flex flex-col gap-3 rounded-2xl border border-border bg-card p-4 shadow-soft transition-transform active:scale-[0.98]"
            >
              <div className={`grid h-11 w-11 place-items-center rounded-xl ${tint}`}>
                <Icon className="h-5 w-5" />
              </div>
              <span className="text-sm font-semibold leading-tight">{label}</span>
            </Link>
          ))}
        </div>
      </section>

      <section className="mt-6 px-5">
        <div className="flex items-start gap-3 rounded-2xl gradient-primary p-4 text-primary-foreground shadow-soft">
          <Bell className="h-5 w-5 shrink-0" />
          <div className="min-w-0">
            <p className="text-sm font-bold">Daily check-in at 9:00 PM</p>
            <p className="mt-1 text-xs text-white/85">We'll ping you when you reach home. Tap to adjust.</p>
          </div>
        </div>
      </section>
    </MobileShell>
  );
}
