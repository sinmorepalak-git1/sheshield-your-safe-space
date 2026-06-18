package com.example.sheshield.ui.screens;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0016\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007\u001a:\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007\u001aB\u0010\n\u001a\u00020\u00012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u001e\u0010\f\u001a\u001a\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\rH\u0007\u001a\u001a\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0013H\u0007\u001a\b\u0010\u0014\u001a\u00020\u0001H\u0007\u001a\b\u0010\u0015\u001a\u00020\u0001H\u0007\u001a\u0016\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u000e\u00a8\u0006\u001a"}, d2 = {"AddContactButton", "", "onClick", "Lkotlin/Function0;", "ContactCard", "contact", "Lcom/example/sheshield/data/ContactEntity;", "onEdit", "onDelete", "onCall", "ContactDialog", "onDismiss", "onSave", "Lkotlin/Function3;", "", "ContactsScreen", "navController", "Landroidx/navigation/NavController;", "viewModel", "Lcom/example/sheshield/ui/viewmodels/ContactViewModel;", "EmptyState", "TipSection", "makeDirectCall", "context", "Landroid/content/Context;", "phoneNumber", "app_debug"})
public final class ContactsScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void ContactsScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.example.sheshield.ui.viewmodels.ContactViewModel viewModel) {
    }
    
    public static final void makeDirectCall(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String phoneNumber) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void EmptyState() {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void AddContactButton(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ContactCard(@org.jetbrains.annotations.NotNull()
    com.example.sheshield.data.ContactEntity contact, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onEdit, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDelete, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onCall) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ContactDialog(@org.jetbrains.annotations.Nullable()
    com.example.sheshield.data.ContactEntity contact, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function3<? super java.lang.String, ? super java.lang.String, ? super java.lang.String, kotlin.Unit> onSave) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void TipSection() {
    }
}