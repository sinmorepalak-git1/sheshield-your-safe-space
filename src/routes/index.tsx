import { createFileRoute, Link, useNavigate } from "@tanstack/react-router";
import { useEffect } from "react";
import { Shield } from "lucide-react";

export const Route = createFileRoute("/")({
  head: () => ({
    meta: [
      { title: "SheShield — Your Safety, Always With You" },
      { name: "description", content: "SheShield is a women safety app with SOS alerts, live location sharing, fake calls, and emergency assistance." },
      { property: "og:title", content: "SheShield — Women Safety App" },
      { property: "og:description", content: "Your Safety, Always With You." },
    ],
  }),
  component: Splash,
});

function Splash() {
  const navigate = useNavigate();
  useEffect(() => {
    const t = setTimeout(() => navigate({ to: "/onboarding" }), 2200);
    return () => clearTimeout(t);
  }, [navigate]);

  return (
    <div className="mx-auto flex min-h-dvh w-full max-w-md flex-col items-center justify-center gradient-primary px-6 text-primary-foreground">
      <div className="relative animate-float-in">
        <div className="absolute inset-0 animate-ripple rounded-full bg-white/30" />
        <div className="absolute inset-0 animate-ripple rounded-full bg-white/20" style={{ animationDelay: "0.6s" }} />
        <div className="relative grid h-28 w-28 place-items-center rounded-full bg-white/95 shadow-2xl">
          <Shield className="h-14 w-14 text-primary" strokeWidth={2.4} />
        </div>
      </div>
      <h1 className="mt-10 text-4xl font-extrabold tracking-tight animate-float-in" style={{ animationDelay: "0.2s" }}>
        SheShield
      </h1>
      <p className="mt-3 text-center text-base text-white/90 animate-float-in" style={{ animationDelay: "0.35s" }}>
        Your Safety, Always With You.
      </p>
      <Link
        to="/onboarding"
        className="mt-16 text-xs uppercase tracking-[0.3em] text-white/70 hover:text-white"
      >
        Tap to continue
      </Link>
    </div>
  );
}
