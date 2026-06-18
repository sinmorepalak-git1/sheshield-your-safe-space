package com.example.sheshield.ui.screens;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u001e\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\bH\u0007\u001a\u0018\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u001a\u001a\u0010\f\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0007\u001a\u0016\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013\u00a8\u0006\u0014"}, d2 = {"ContactNotifyItem", "", "contact", "Lcom/example/sheshield/data/ContactEntity;", "SOSActiveContent", "navController", "Landroidx/navigation/NavController;", "contacts", "", "SOSCountdownContent", "count", "", "SOSScreen", "viewModel", "Lcom/example/sheshield/ui/viewmodels/ContactViewModel;", "makeSOSCall", "context", "Landroid/content/Context;", "phoneNumber", "", "app_debug"})
public final class SOSScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void SOSScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.example.sheshield.ui.viewmodels.ContactViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void SOSCountdownContent(int count, @org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void SOSActiveContent(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    java.util.List<com.example.sheshield.data.ContactEntity> contacts) {
    }
    
    public static final void makeSOSCall(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String phoneNumber) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ContactNotifyItem(@org.jetbrains.annotations.NotNull()
    com.example.sheshield.data.ContactEntity contact) {
    }
}