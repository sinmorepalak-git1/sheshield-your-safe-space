import { createFileRoute, Link } from "@tanstack/react-router";
import { Mail, Lock, User, Phone } from "lucide-react";
import { Field } from "./login";

export const Route = createFileRoute("/signup")({
  component: Signup,
});

function Signup() {
  return (
    <div className="mx-auto flex min-h-dvh w-full max-w-md flex-col bg-background px-6 py-10">
      <div className="mb-8">
        <h1 className="text-3xl font-extrabold tracking-tight">Create account</h1>
        <p className="mt-2 text-sm text-muted-foreground">Join SheShield and build your safety circle.</p>
      </div>
      <form className="space-y-4" onSubmit={(e) => e.preventDefault()}>
        <Field icon={<User className="h-5 w-5" />} placeholder="Full name" defaultValue="Priya Sharma" />
        <Field icon={<Mail className="h-5 w-5" />} type="email" placeholder="Email" defaultValue="priya@example.com" />
        <Field icon={<Phone className="h-5 w-5" />} type="tel" placeholder="Phone number" defaultValue="+91 98765 43210" />
        <Field icon={<Lock className="h-5 w-5" />} type="password" placeholder="Password" defaultValue="••••••••" />
        <Link to="/home" className="mt-2 flex h-14 w-full items-center justify-center rounded-2xl gradient-primary text-base font-semibold text-primary-foreground shadow-soft">
          Create Account
        </Link>
      </form>
      <p className="mt-auto pt-10 text-center text-sm text-muted-foreground">
        Already have one?{" "}
        <Link to="/login" className="font-semibold text-primary">Sign in</Link>
      </p>
    </div>
  );
}
