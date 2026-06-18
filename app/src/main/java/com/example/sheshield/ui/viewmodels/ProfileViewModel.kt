package com.example.sheshield.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sheshield.data.User
import com.example.sheshield.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val repository = UserRepository()
    private val auth = FirebaseAuth.getInstance()

    private val _userProfile = MutableStateFlow<User?>(null)
    val userProfile: StateFlow<User?> = _userProfile.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _updateStatus = MutableStateFlow<Result<Unit>?>(null)
    val updateStatus: StateFlow<Result<Unit>?> = _updateStatus.asStateFlow()

    init {
        fetchUserProfile()
    }

    fun fetchUserProfile() {
        viewModelScope.launch {
            _isLoading.value = true
            val result = repository.getUserProfile()
            result.onSuccess {
                _userProfile.value = it
            }.onFailure {
                // Fallback to Firebase Auth basic info if Firestore fetch fails
                _userProfile.value = User(
                    uid = auth.currentUser?.uid ?: "",
                    email = auth.currentUser?.email ?: "",
                    name = auth.currentUser?.displayName ?: ""
                )
            }
            _isLoading.value = false
        }
    }

    fun updateUserProfile(name: String, phone: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val result = repository.updateUserProfile(name, phone)
            _updateStatus.value = result
            if (result.isSuccess) {
                fetchUserProfile() // Refresh data
            }
            _isLoading.value = false
        }
    }
    
    fun resetUpdateStatus() {
        _updateStatus.value = null
    }

    fun getUserInitial(user: User?): String {
        return when {
            user == null -> "U"
            !user.name.isNullOrBlank() -> user.name[0].uppercaseChar().toString()
            !user.email.isNullOrBlank() -> user.email[0].uppercaseChar().toString()
            else -> "U"
        }
    }
}
