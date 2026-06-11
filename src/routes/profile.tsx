import { createFileRoute, Link } from "@tanstack/react-router";
import { useState } from "react";
import { MobileShell, ScreenHeader } from "@/components/mobile-shell";
import { Bell, Lock, Moon, Sun, LogOut, ChevronRight, Mic, Globe, HelpCircle } from "lucide-react";

export const Route = createFileRoute("/profile")({
  component: Profile,
});

function Profile() {
  const [dark, setDark] = useState(false);
  const [notif, setNotif] = useState(true);
  const [stealth, setStealth] = useState(true);

  return (
    <MobileShell>
      <ScreenHeader title="Profile & Settings" back="/home" />
      <div className="px-5 pt-4">
        <div className="flex items-center gap-4 rounded-3xl gradient-primary p-5 text-primary-foreground shadow-soft">
          <div className="grid h-16 w-16 place-items-center rounded-full bg-white/25 text-2xl font-bold backdrop-blur">P</div>
          <div className="min-w-0 flex-1">
            <p className="truncate text-lg font-extrabold">Priya Sharma</p>
            <p className="truncate text-xs text-white/85">priya@example.com</p>
            <p className="truncate text-xs text-white/85">+91 98765 43210</p>
          </div>
          <button aria-label="Edit profile" className="rounded-full bg-white/20 px-3 py-1.5 text-xs font-bold">Edit</button>
        </div>

        <div className="mt-4 grid grid-cols-3 gap-3 text-center">
          <Stat label="Contacts" value="5" />
          <Stat label="Safe zones" value="3" />
          <Stat label="Check-ins" value="42" />
        </div>
      </div>

      <Group title="Preferences">
        <Row icon={dark ? <Moon className="h-4 w-4"/> : <Sun className="h-4 w-4"/>} label="Dark mode" trail={<Toggle on={dark} onChange={() => { setDark(!dark); document.documentElement.classList.toggle("dark"); }} />} />
        <Row icon={<Bell className="h-4 w-4"/>} label="Notifications" sub="SOS, check-in & safety alerts" trail={<Toggle on={notif} onChange={() => setNotif(!notif)} />} />
        <Row icon={<Mic className="h-4 w-4"/>} label="Voice & shake" sub="Hands-free SOS triggers" to="/voice-shake" />
        <Row icon={<Globe className="h-4 w-4"/>} label="Language" sub="English (India)" trail={<ChevronRight className="h-4 w-4 text-muted-foreground"/>} />
      </Group>

      <Group title="Privacy">
        <Row icon={<Lock className="h-4 w-4"/>} label="Stealth mode" sub="Disguise app icon as calculator" trail={<Toggle on={stealth} onChange={() => setStealth(!stealth)} />} />
        <Row icon={<Lock className="h-4 w-4"/>} label="App lock" sub="Require PIN to open" trail={<ChevronRight className="h-4 w-4 text-muted-foreground"/>} />
      </Group>

      <Group title="Support">
        <Row icon={<HelpCircle className="h-4 w-4"/>} label="Help & FAQ" trail={<ChevronRight className="h-4 w-4 text-muted-foreground"/>} />
        <Row icon={<HelpCircle className="h-4 w-4"/>} label="About SheShield" sub="Version 1.0.0" trail={<ChevronRight className="h-4 w-4 text-muted-foreground"/>} />
      </Group>

      <div className="px-5 pb-4 pt-2">
        <Link to="/login" className="flex h-13 w-full items-center justify-center gap-2 rounded-2xl border border-sos/30 bg-sos/5 py-4 text-sm font-bold text-sos">
          <LogOut className="h-4 w-4" /> Log out
        </Link>
      </div>
    </MobileShell>
  );
}

function Stat({ label, value }: { label: string; value: string }) {
  return (
    <div className="rounded-2xl border border-border bg-card p-3">
      <p className="text-lg font-extrabold">{value}</p>
      <p className="text-[10px] uppercase tracking-wider text-muted-foreground">{label}</p>
    </div>
  );
}

function Group({ title, children }: { title: string; children: React.ReactNode }) {
  return (
    <section className="mt-6 px-5">
      <h2 className="mb-2 px-1 text-xs font-bold uppercase tracking-wider text-muted-foreground">{title}</h2>
      <div className="overflow-hidden rounded-2xl border border-border bg-card shadow-soft divide-y divide-border">{children}</div>
    </section>
  );
}

function Row({ icon, label, sub, trail, to }: { icon: React.ReactNode; label: string; sub?: string; trail?: React.ReactNode; to?: string }) {
  const inner = (
    <div className="flex items-center gap-3 p-4">
      <div className="grid h-9 w-9 shrink-0 place-items-center rounded-xl bg-secondary text-primary">{icon}</div>
      <div className="min-w-0 flex-1">
        <p className="truncate text-sm font-semibold">{label}</p>
        {sub && <p className="truncate text-xs text-muted-foreground">{sub}</p>}
      </div>
      {trail ?? <ChevronRight className="h-4 w-4 text-muted-foreground" />}
    </div>
  );
  return to ? <Link to={to}>{inner}</Link> : <div>{inner}</div>;
}

function Toggle({ on, onChange }: { on: boolean; onChange: () => void }) {
  return (
    <button onClick={onChange} aria-pressed={on} className={`relative h-7 w-12 shrink-0 rounded-full transition-colors ${on ? "bg-primary" : "bg-muted"}`}>
      <span className={`absolute top-0.5 h-6 w-6 rounded-full bg-white shadow transition-all ${on ? "left-[22px]" : "left-0.5"}`} />
    </button>
  );
}
