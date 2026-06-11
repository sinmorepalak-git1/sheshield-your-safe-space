import { createFileRoute } from "@tanstack/react-router";
import { Phone, MessageCircle, Pencil, Trash2, Plus } from "lucide-react";
import { MobileShell, ScreenHeader } from "@/components/mobile-shell";

export const Route = createFileRoute("/contacts")({
  component: Contacts,
});

const data = [
  { name: "Mom", relation: "Family", phone: "+91 98765 12345", initial: "M", tint: "from-rose-300 to-pink-400" },
  { name: "Aarav", relation: "Brother", phone: "+91 99887 65432", initial: "A", tint: "from-fuchsia-300 to-purple-400" },
  { name: "Neha", relation: "Best Friend", phone: "+91 90123 45678", initial: "N", tint: "from-violet-300 to-pink-400" },
  { name: "Dad", relation: "Family", phone: "+91 98123 33445", initial: "D", tint: "from-pink-300 to-rose-400" },
  { name: "Riya", relation: "Colleague", phone: "+91 91234 78901", initial: "R", tint: "from-purple-300 to-fuchsia-400" },
];

function Contacts() {
  return (
    <MobileShell>
      <ScreenHeader title="Emergency Contacts" subtitle="People we'll alert during SOS" back="/home" />
      <div className="px-5 pt-4">
        <button className="flex h-14 w-full items-center justify-center gap-2 rounded-2xl border-2 border-dashed border-primary/40 bg-primary/5 font-semibold text-primary active:scale-[0.98]">
          <Plus className="h-5 w-5" /> Add trusted contact
        </button>
      </div>

      <ul className="mt-5 space-y-3 px-5">
        {data.map((c, i) => (
          <li key={c.name} className="flex items-center gap-3 rounded-2xl border border-border bg-card p-3 shadow-soft animate-float-in" style={{ animationDelay: `${i * 0.05}s` }}>
            <div className={`grid h-12 w-12 shrink-0 place-items-center rounded-full bg-gradient-to-br ${c.tint} text-lg font-bold text-white`}>
              {c.initial}
            </div>
            <div className="min-w-0 flex-1">
              <div className="flex items-center gap-2">
                <p className="truncate text-sm font-bold">{c.name}</p>
                <span className="rounded-full bg-secondary px-2 py-0.5 text-[10px] font-semibold text-secondary-foreground">{c.relation}</span>
              </div>
              <p className="truncate text-xs text-muted-foreground">{c.phone}</p>
            </div>
            <div className="flex shrink-0 gap-1">
              <button aria-label="Call" className="grid h-9 w-9 place-items-center rounded-full bg-success/15 text-success"><Phone className="h-4 w-4"/></button>
              <button aria-label="Message" className="grid h-9 w-9 place-items-center rounded-full bg-primary/15 text-primary"><MessageCircle className="h-4 w-4"/></button>
            </div>
          </li>
        ))}
      </ul>

      <div className="mt-6 px-5">
        <div className="rounded-2xl border border-border bg-muted/40 p-4 text-xs text-muted-foreground">
          <p className="font-semibold text-foreground">Tip</p>
          Add at least 3 trusted contacts. Swipe a card to <span className="inline-flex items-center gap-1 font-semibold text-foreground"><Pencil className="h-3 w-3"/> edit</span> or <span className="inline-flex items-center gap-1 font-semibold text-destructive"><Trash2 className="h-3 w-3"/> remove</span>.
        </div>
      </div>
    </MobileShell>
  );
}
