import { createFileRoute, Link } from "@tanstack/react-router";
import { Mail, KeyRound } from "lucide-react";
import { Field } from "./login";

export const Route = createFileRoute("/forgot")({
  component: Forgot,
});

function Forgot() {
  return (
    <div className="mx-auto flex min-h-dvh w-full max-w-md flex-col bg-background px-6 py-10">
      <div className="grid h-16 w-16 place-items-center rounded-2xl bg-secondary text-primary">
        <KeyRound className="h-7 w-7" />
      </div>
      <h1 className="mt-6 text-3xl font-extrabold tracking-tight">Reset password</h1>
      <p className="mt-2 text-sm text-muted-foreground">
        We'll send a secure link to your email so you can get back into SheShield safely.
      </p>
      <form className="mt-8 space-y-4" onSubmit={(e) => e.preventDefault()}>
        <Field icon={<Mail className="h-5 w-5" />} type="email" placeholder="Your email" defaultValue="priya@example.com" />
        <Link to="/login" className="flex h-14 w-full items-center justify-center rounded-2xl gradient-primary text-base font-semibold text-primary-foreground shadow-soft">
          Send reset link
        </Link>
      </form>
      <Link to="/login" className="mt-auto pt-10 text-center text-sm font-semibold text-primary">
        Back to sign in
      </Link>
    </div>
  );
}
