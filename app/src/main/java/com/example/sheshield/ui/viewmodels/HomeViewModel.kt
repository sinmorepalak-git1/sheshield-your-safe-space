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
import java.util.Locale

class HomeViewModel : ViewModel() {
    private val userRepository = UserRepository()
    private val auth = FirebaseAuth.getInstance()

    private val _userProfile = MutableStateFlow<User?>(null)
    val userProfile: StateFlow<User?> = _userProfile.asStateFlow()

    init {
        fetchUserData()
    }

    fun fetchUserData() {
        viewModelScope.launch {
            val result = userRepository.getUserProfile()
            result.onSuccess {
                _userProfile.value = it
            }.onFailure {
                val currentUser = auth.currentUser
                if (currentUser != null) {
                    _userProfile.value = User(
                        uid = currentUser.uid,
                        email = currentUser.email ?: "",
                        name = currentUser.displayName ?: ""
                    )
                }
            }
        }
    }

    fun getGreetingName(user: User?): String {
        return when {
            user == null -> "User"
            !user.name.isNullOrBlank() -> user.name
            !user.email.isNullOrBlank() -> {
                user.email.split("@")[0].replaceFirstChar { 
                    if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() 
                }
            }
            else -> "User"
        }
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
