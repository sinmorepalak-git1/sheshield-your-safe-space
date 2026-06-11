import { createFileRoute, Link } from "@tanstack/react-router";
import { Mail, Lock, Eye } from "lucide-react";
import { useState } from "react";

export const Route = createFileRoute("/login")({
  component: Login,
});

function Login() {
  const [show, setShow] = useState(false);
  return (
    <div className="mx-auto flex min-h-dvh w-full max-w-md flex-col bg-background px-6 py-10">
      <div className="mb-8">
        <h1 className="text-3xl font-extrabold tracking-tight">Welcome back</h1>
        <p className="mt-2 text-sm text-muted-foreground">Sign in to keep your circle close and safe.</p>
      </div>

      <form className="space-y-4" onSubmit={(e) => e.preventDefault()}>
        <Field icon={<Mail className="h-5 w-5" />} type="email" placeholder="Email address" defaultValue="priya@example.com" />
        <div className="relative">
          <Field icon={<Lock className="h-5 w-5" />} type={show ? "text" : "password"} placeholder="Password" defaultValue="••••••••" />
          <button type="button" onClick={() => setShow(!show)} aria-label="Toggle password" className="absolute right-4 top-1/2 -translate-y-1/2 text-muted-foreground">
            <Eye className="h-5 w-5" />
          </button>
        </div>

        <div className="flex justify-end">
          <Link to="/forgot" className="text-xs font-semibold text-primary">Forgot password?</Link>
        </div>

        <Link to="/home" className="flex h-14 w-full items-center justify-center rounded-2xl gradient-primary text-base font-semibold text-primary-foreground shadow-soft transition-transform active:scale-[0.98]">
          Sign In
        </Link>
      </form>

      <div className="my-6 flex items-center gap-3 text-xs text-muted-foreground">
        <span className="h-px flex-1 bg-border" />
        OR
        <span className="h-px flex-1 bg-border" />
      </div>

      <Link to="/home" className="flex h-14 w-full items-center justify-center gap-3 rounded-2xl border border-border bg-card text-sm font-semibold text-foreground transition-transform active:scale-[0.98]">
        <GoogleIcon /> Continue with Google
      </Link>

      <p className="mt-auto pt-10 text-center text-sm text-muted-foreground">
        New here?{" "}
        <Link to="/signup" className="font-semibold text-primary">Create account</Link>
      </p>
    </div>
  );
}

export function Field({ icon, ...props }: { icon: React.ReactNode } & React.InputHTMLAttributes<HTMLInputElement>) {
  return (
    <label className="flex h-14 items-center gap-3 rounded-2xl border border-border bg-card px-4 focus-within:ring-2 focus-within:ring-ring">
      <span className="text-muted-foreground">{icon}</span>
      <input {...props} className="min-w-0 flex-1 bg-transparent text-sm text-foreground outline-none placeholder:text-muted-foreground" />
    </label>
  );
}

function GoogleIcon() {
  return (
    <svg width="20" height="20" viewBox="0 0 48 48">
      <path fill="#FFC107" d="M43.6 20.5H42V20H24v8h11.3C33.7 32.4 29.3 35.5 24 35.5c-6.4 0-11.5-5.1-11.5-11.5S17.6 12.5 24 12.5c2.9 0 5.6 1.1 7.6 2.9l5.7-5.7C33.6 6.3 29 4.5 24 4.5 13.2 4.5 4.5 13.2 4.5 24S13.2 43.5 24 43.5 43.5 34.8 43.5 24c0-1.2-.1-2.3-.4-3.5z"/>
      <path fill="#FF3D00" d="M6.3 14.7l6.6 4.8C14.7 16 19 12.5 24 12.5c2.9 0 5.6 1.1 7.6 2.9l5.7-5.7C33.6 6.3 29 4.5 24 4.5c-7.6 0-14.1 4.3-17.7 10.2z"/>
      <path fill="#4CAF50" d="M24 43.5c5 0 9.5-1.9 12.9-5l-6-5c-1.9 1.3-4.3 2.1-6.9 2.1-5.3 0-9.7-3.1-11.3-7.5l-6.5 5C9.5 39.6 16.2 43.5 24 43.5z"/>
      <path fill="#1976D2" d="M43.6 20.5H42V20H24v8h11.3c-.8 2.2-2.2 4.1-4 5.5l6 5c-.4.4 6.5-4.8 6.5-14.5 0-1.2-.1-2.3-.4-3.5z"/>
    </svg>
  );
}
