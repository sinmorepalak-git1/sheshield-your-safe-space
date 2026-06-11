import { ReactNode } from "react";
import { Link, useRouterState } from "@tanstack/react-router";
import { Home, Users, MapPin, Settings, Bell } from "lucide-react";
import { cn } from "@/lib/utils";

interface MobileShellProps {
  children: ReactNode;
  showNav?: boolean;
  className?: string;
}

const navItems = [
  { to: "/home", label: "Home", icon: Home },
  { to: "/contacts", label: "Contacts", icon: Users },
  { to: "/location", label: "Location", icon: MapPin },
  { to: "/tips", label: "Tips", icon: Bell },
  { to: "/profile", label: "Profile", icon: Settings },
] as const;

export function MobileShell({ children, showNav = true, className }: MobileShellProps) {
  const pathname = useRouterState({ select: (s) => s.location.pathname });
  return (
    <div className="mx-auto flex min-h-dvh w-full max-w-md flex-col bg-background">
      <main className={cn("flex-1 pb-24", className)}>{children}</main>
      {showNav && (
        <nav className="fixed bottom-0 left-1/2 z-40 w-full max-w-md -translate-x-1/2 border-t border-border bg-card/95 backdrop-blur-xl">
          <ul className="grid grid-cols-5 px-2 pb-3 pt-2">
            {navItems.map(({ to, label, icon: Icon }) => {
              const active = pathname === to;
              return (
                <li key={to}>
                  <Link
                    to={to}
                    className="flex flex-col items-center gap-1 rounded-xl px-1 py-2 transition-colors"
                  >
                    <div
                      className={cn(
                        "flex h-9 w-12 items-center justify-center rounded-full transition-all",
                        active && "bg-secondary"
                      )}
                    >
                      <Icon
                        className={cn(
                          "h-5 w-5 transition-colors",
                          active ? "text-primary" : "text-muted-foreground"
                        )}
                      />
                    </div>
                    <span
                      className={cn(
                        "text-[10px] font-medium transition-colors",
                        active ? "text-primary" : "text-muted-foreground"
                      )}
                    >
                      {label}
                    </span>
                  </Link>
                </li>
              );
            })}
          </ul>
        </nav>
      )}
    </div>
  );
}

export function ScreenHeader({
  title,
  subtitle,
  back,
  right,
}: {
  title: string;
  subtitle?: string;
  back?: string;
  right?: ReactNode;
}) {
  return (
    <header className="sticky top-0 z-30 flex items-center gap-3 border-b border-border/60 bg-background/85 px-4 py-4 backdrop-blur-xl">
      {back && (
        <Link
          to={back}
          aria-label="Back"
          className="grid h-10 w-10 shrink-0 place-items-center rounded-full bg-secondary text-secondary-foreground transition-transform active:scale-95"
        >
          <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2.5" strokeLinecap="round" strokeLinejoin="round"><path d="m15 18-6-6 6-6"/></svg>
        </Link>
      )}
      <div className="min-w-0 flex-1">
        <h1 className="truncate text-lg font-bold tracking-tight">{title}</h1>
        {subtitle && <p className="truncate text-xs text-muted-foreground">{subtitle}</p>}
      </div>
      {right}
    </header>
  );
}
