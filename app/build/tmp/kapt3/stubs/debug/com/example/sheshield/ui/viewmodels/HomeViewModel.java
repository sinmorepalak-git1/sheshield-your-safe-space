package com.example.sheshield.ui.viewmodels;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u000e\u001a\u00020\u000fJ\u0010\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0005J\u0010\u0010\u0013\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0005R\u0016\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/example/sheshield/ui/viewmodels/HomeViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_userProfile", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/sheshield/data/User;", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "userProfile", "Lkotlinx/coroutines/flow/StateFlow;", "getUserProfile", "()Lkotlinx/coroutines/flow/StateFlow;", "userRepository", "Lcom/example/sheshield/repository/UserRepository;", "fetchUserData", "", "getGreetingName", "", "user", "getUserInitial", "app_debug"})
public final class HomeViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.sheshield.repository.UserRepository userRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.auth.FirebaseAuth auth = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.sheshield.data.User> _userProfile = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.example.sheshield.data.User> userProfile = null;
    
    public HomeViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.example.sheshield.data.User> getUserProfile() {
        return null;
    }
    
    public final void fetchUserData() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getGreetingName(@org.jetbrains.annotations.Nullable()
    com.example.sheshield.data.User user) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getUserInitial(@org.jetbrains.annotations.Nullable()
    com.example.sheshield.data.User user) {
        return null;
    }
}